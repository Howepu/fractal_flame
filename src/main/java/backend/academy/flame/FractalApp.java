package backend.academy.flame;

import backend.academy.flame.entities.Rect;
import backend.academy.flame.image.FractalImage;
import backend.academy.flame.image.FractalRenderer;
import backend.academy.flame.image.GammaCorrection;
import backend.academy.flame.image.ImageFormat;
import backend.academy.flame.image.ImageProcessor;
import backend.academy.flame.image.ImageUtils;
import backend.academy.flame.transformations.EyefishTransformation;
import backend.academy.flame.transformations.HeartTransformation;
import backend.academy.flame.transformations.SpiralTransformation;
import backend.academy.flame.transformations.SquareTransformation;
import backend.academy.flame.transformations.SwirlTransformation;
import backend.academy.flame.transformations.TangentTransfortmation;
import backend.academy.flame.transformations.Transformation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
public class FractalApp {
    private static final int SAMPLES = 100000;
    private static final int THREADS = 4;

    private FractalApp() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void main(String[] args) throws Exception {
        logSystemConfiguration(); // Вывод конфигурации системы

        Scanner scanner = new Scanner(System.in);
        log.info("Введите ширину изображения (например, 1920):");
        int width = scanner.nextInt();
        scanner.nextLine(); // Очистка после nextInt()

        log.info("Введите высоту изображения (например, 1080):");
        int height = scanner.nextInt();
        scanner.nextLine(); // Очистка после nextInt()

        log.info("Введите количество итераций (например, 50):");
        int iterations = scanner.nextInt();
        scanner.nextLine(); // Очистка после nextInt()

        log.info("Введите формат изображения (например, PNG):");
        String format = scanner.nextLine().trim().toUpperCase();

        // Определение координат для фрактала
        Rect world = new Rect(-10, -10, 20, 20); // Координаты мира

        // Ввод трансформаций
        List<Transformation> transformations = new ArrayList<>();
        log.info("Введите трансформации (1-Eyefish, 2-Heart, 3-Spiral, 4-Square, 5-Swirl, 6-Tangent):"); // Пример: 1, 3, 5
        String transformationsInput = scanner.nextLine();
        for (String trStr : transformationsInput.split(",")) {
            int tr = Integer.parseInt(trStr.trim());
            switch (tr) {
                case 1 -> transformations.add(new EyefishTransformation());
                case 2 -> transformations.add(new HeartTransformation());
                case 3 -> transformations.add(new SpiralTransformation());
                case 4 -> transformations.add(new SquareTransformation());
                case 5 -> transformations.add(new SwirlTransformation());
                case 6 -> transformations.add(new TangentTransfortmation());
                default -> log.warn("Неизвестная трансформация: {}", tr);
            }
        }

        FractalImage canvas = FractalImage.create(width, height);
        FractalRenderer renderer = new FractalRenderer();

        // Многопоточная обработка
        log.info("Начинаем многопоточную обработку...");
        long startTimeMulti = System.currentTimeMillis();
        FractalImage renderedImageMultiThreaded = renderer.renderMultithreaded(canvas, world, transformations, SAMPLES,
            iterations, THREADS);
        long endTimeMulti = System.currentTimeMillis();
        log.info("Многопоточная обработка заняла: {} мс", (endTimeMulti - startTimeMulti));

        // Однопоточная обработка
        log.info("Начинаем однопоточную обработку...");
        long startTime = System.currentTimeMillis();
        FractalImage renderedImageSingleThreaded = renderer.renderSingleThreaded(canvas, world, transformations,
            SAMPLES, iterations);
        long endTime = System.currentTimeMillis();
        log.info("Однопоточная обработка заняла: {} мс", (endTime - startTime));

        // Разница во времени
        log.info("Разница во времени (многопоточный - однопоточный): {} мс",
            ((endTime - startTime) - (endTimeMulti - startTimeMulti)));

        // Пост-обработка (гамма-коррекция)
        log.info("Применяем гамма-коррекцию к изображению...");
        ImageProcessor gammaProcessor = new GammaCorrection(2.2, 1);
        gammaProcessor.process(renderedImageMultiThreaded);

        // Сохранение изображения
        Path outputPath = Paths.get("fractal_flame.png");
        ImageUtils.save(renderedImageMultiThreaded, outputPath, ImageFormat.valueOf(format));
        log.info("Фрактал сохранен в {}", outputPath);

        // Подтверждение завершения программы
        log.info("Программа завершена. Выход.");
    }

    private static void logSystemConfiguration() {
        Runtime runtime = Runtime.getRuntime();

        log.info("=== Конфигурация системы ===");
        log.info("Операционная система: {} {}", System.getProperty("os.name"), System.getProperty("os.version"));
        log.info("Архитектура процессора: {}", System.getProperty("os.arch"));
        log.info("Ядра процессора (доступные): {}", runtime.availableProcessors());
        log.info("Объем памяти (максимум): {} MB", runtime.maxMemory() / (1024 * 1024));
        log.info("Объем памяти (всего): {} MB", runtime.totalMemory() / (1024 * 1024));
        log.info("Объем свободной памяти: {} MB", runtime.freeMemory() / (1024 * 1024));
        log.info("=============================");
    }
}
