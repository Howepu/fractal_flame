package backend.academy.flame;

import backend.academy.flame.transformations.Transformation;
import backend.academy.flame.image.FractalRenderer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
public class FractalApp {
    private static final int SAMPLES = 100000;
    private static final int THREADS = 4;
    private static final String CORRECTION = "Применяем гамма-коррекцию к изображению...";
    private static final Random RANDOM = new Random();

    private FractalApp() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void main(String[] args) throws Exception {
//        logSystemConfiguration(); // Вывод конфигурации системы

        log.info("Используется {} потока", THREADS);

        Scanner scanner = new Scanner(System.in);
        Path outputPath = Paths.get("fractal_flame");

        // Проверка ширины
        int width = Checker.readPositiveInt(scanner, "Введите ширину изображения (например, 1920):");

        // Проверка высоты
        int height = Checker.readPositiveInt(scanner, "Введите высоту изображения (например, 1080):");

        // Проверка количества итераций
        int iterations = Checker.readPositiveInt(scanner, "Введите количество итераций (например, 50):");

        // Проверка формата изображения
        String format = Checker.readImageFormat(scanner);
        outputPath = Path.of(outputPath + "." + format.toLowerCase());

        // Ввод трансформаций

        List<Transformation> transformations = new ArrayList<>();

        log.info("Введите трансформацию: (1-VortexFlower, 2-Eyefish, 3-Heart, 4-Spiral, 5-Swirl, 6-Waves, 7-Tangent, "
            + "8-Cross, 9-Hyperbolic, 10-Circular)");

//        int tr = Checker.readPositiveInt(scanner, "Введите номер трансформации:");
//
//        // Переменная для одной трансформации
//       Transformation transformation = null;
//
//        switch (tr) {
//            case 1 -> transformation = new EyefishTransformation();
//            case 2 -> transformation = new HeartTransformation();
//            //case 3 -> transformation = new SpiralTransformation();
//            //case 4 -> transformation = new SwirlTransformation();
//            case 5 -> transformation = new WavesTransformation();
//            case 6 -> transformation = new TangentTransformation();
//            case 7 -> transformation = new CrossTransformation();
//            case 8 -> transformation = new HyperbolicTransformation();
//            case 9 -> transformation = new CircularTransformation();
//            default -> log.warn("Неизвестная трансформация: {}", tr);
//        }

        FractalRenderer.renderSingleThreaded(SAMPLES, iterations, width, height, RANDOM);

//        log.info("Выберите режим запуска: (1-многопоточный или 2-однопоточный)");
//        int threading = Checker.readOneOrTwo(scanner);
//        if (threading == 1) {
//            // Многопоточная обработка
//            log.info("Начинаем многопоточную обработку...");
//            long startTimeMulti = System.currentTimeMillis();
//            FractalImage renderedImageMultiThreaded = renderer.renderMultithreaded(canvas, world, transformations,
//                SAMPLES, iterations, THREADS);
//            long endTimeMulti = System.currentTimeMillis();
//            log.info("Многопоточная обработка заняла: {} мс", (endTimeMulti - startTimeMulti));
//            // Пост-обработка (гамма-коррекция)
//            log.info(CORRECTION);
//            ImageProcessor gammaProcessor = new GammaCorrection(5, 5);
//            gammaProcessor.process(renderedImageMultiThreaded);
//            // Сохранение изображения
//            ImageUtils.save(renderedImageMultiThreaded, outputPath, ImageFormat.valueOf(format));
//        } else {
//            // Однопоточная обработка
//            log.info("Начинаем однопоточную обработку...");
//            long startTime = System.currentTimeMillis();
//            FractalImage renderedImageSingleThreaded = renderer.renderSingleThreaded(canvas, world, transformations,
//                SAMPLES, iterations);
//            long endTime = System.currentTimeMillis();
//            log.info("Однопоточная обработка заняла: {} мс", (endTime - startTime));
//            // Пост-обработка (гамма-коррекция)
//            log.info(CORRECTION);
//            ImageProcessor gammaProcessor = new GammaCorrection(5, 5);
//            gammaProcessor.process(renderedImageSingleThreaded);
//            // Сохранение изображения
//            ImageUtils.save(renderedImageSingleThreaded, outputPath, ImageFormat.valueOf(format));
//        }
//        log.info("Фрактал сохранен в {}", outputPath);
//
//        // Подтверждение завершения программы
//        log.info("Программа завершена. Выход.");
//    }

//        private static void logSystemConfiguration() {
//            Runtime runtime = Runtime.getRuntime();
//
//            log.info("=== Конфигурация системы ===");
//            log.info("Операционная система: {} {}", System.getProperty("os.name"), System.getProperty("os.version"));
//            log.info("Архитектура процессора: {}", System.getProperty("os.arch"));
//            log.info("Ядра процессора (доступные): {}", runtime.availableProcessors());
//            log.info("Объем памяти (максимум): {} MB", runtime.maxMemory() / (1024 * 1024));
//            log.info("Объем памяти (всего): {} MB", runtime.totalMemory() / (1024 * 1024));
//            log.info("Объем свободной памяти: {} MB", runtime.freeMemory() / (1024 * 1024));
//            log.info("=============================");
//        }
    }


}
