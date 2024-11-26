package backend.academy.flame;

import backend.academy.flame.entities.Rect;
import backend.academy.flame.image.FractalImage;
import backend.academy.flame.image.FractalRenderer;
import backend.academy.flame.image.GammaCorrection;
import backend.academy.flame.image.ImageFormat;
import backend.academy.flame.image.ImageProcessor;
import backend.academy.flame.image.ImageUtils;
import backend.academy.flame.transformations.CircularTransformation;
import backend.academy.flame.transformations.CrossTransformation;
import backend.academy.flame.transformations.EyefishTransformation;
import backend.academy.flame.transformations.HeartTransformation;
import backend.academy.flame.transformations.HyperbolicTransformation;
import backend.academy.flame.transformations.SpiralTransformation;
import backend.academy.flame.transformations.SwirlTransformation;
import backend.academy.flame.transformations.TangentTransfortmation;
import backend.academy.flame.transformations.Transformation;
import backend.academy.flame.transformations.WavesTransformation;
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
    private static final String CORRECTION = "Применяем гамма-коррекцию к изображению...";

    private FractalApp() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void main(String[] args) throws Exception {
        logSystemConfiguration(); // Вывод конфигурации системы

        log.info("Используется {} потока", THREADS);

        Scanner scanner = new Scanner(System.in);
        Path outputPath = Paths.get("fractal_flame");

        // Проверка ширины
        int width = readPositiveInt(scanner, "Введите ширину изображения (например, 1920):");

        // Проверка высоты
        int height = readPositiveInt(scanner, "Введите высоту изображения (например, 1080):");

        // Проверка количества итераций
        int iterations = readPositiveInt(scanner, "Введите количество итераций (например, 50):");

        // Проверка формата изображения
        String format = readImageFormat(scanner);
        outputPath = Path.of(outputPath + "." + format.toLowerCase());

        // Определение координат для фрактала
        Rect world = new Rect(-10, -10, 20, 20); // Координаты мира

        // Ввод трансформаций
        List<Transformation> transformations = new ArrayList<>();
        log.info("Введите трансформации: (1-Eyefish, 2-Heart, 3-Spiral, 4-Swirl, 5-Waves, 6-Tangent, 7-Cross, "
            + "8-Hyperbolic, 9-Circular)");
        String transformationsInput = scanner.nextLine();
        for (String trStr : transformationsInput.split(",")) {
            try {
                int tr = Integer.parseInt(trStr.trim());
                double[] coefficients = readCoefficients(scanner); // Считывание коэффициентов
                switch (tr) {
                    case 1 -> transformations.add(new EyefishTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 2 -> transformations.add(new HeartTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 3 -> transformations.add(new SpiralTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 4 -> transformations.add(new SwirlTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 5 -> transformations.add(new WavesTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 6 -> transformations.add(new TangentTransfortmation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 7 -> transformations.add(new CrossTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 8 -> transformations.add(new HyperbolicTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    case 9 -> transformations.add(new CircularTransformation(coefficients[0], coefficients[1],
                        coefficients[2], coefficients[3], coefficients[4], coefficients[5]));
                    default -> log.warn("Неизвестная трансформация: {}", tr);
                }
            } catch (NumberFormatException e) {
                log.warn("Некорректный формат трансформации: {}", trStr);
            }
        }

        FractalImage canvas = FractalImage.create(width, height);
        FractalRenderer renderer = new FractalRenderer();
        log.info("Выберите режим запуска: (1-многопоточный или 2-однопоточный)");
        int threading = readOneOrTwo(scanner);
        if (threading == 1) {
            // Многопоточная обработка
            log.info("Начинаем многопоточную обработку...");
            long startTimeMulti = System.currentTimeMillis();
            FractalImage renderedImageMultiThreaded = renderer.renderMultithreaded(canvas, world, transformations,
                SAMPLES, iterations, THREADS);
            long endTimeMulti = System.currentTimeMillis();
            log.info("Многопоточная обработка заняла: {} мс", (endTimeMulti - startTimeMulti));
            // Пост-обработка (гамма-коррекция)
            log.info(CORRECTION);
            ImageProcessor gammaProcessor = new GammaCorrection(2.2, 1);
            gammaProcessor.process(renderedImageMultiThreaded);
            // Сохранение изображения
            ImageUtils.save(renderedImageMultiThreaded, outputPath, ImageFormat.valueOf(format));
        } else {
            // Однопоточная обработка
            log.info("Начинаем однопоточную обработку...");
            long startTime = System.currentTimeMillis();
            FractalImage renderedImageSingleThreaded = renderer.renderSingleThreaded(canvas, world, transformations,
                SAMPLES, iterations);
            long endTime = System.currentTimeMillis();
            log.info("Однопоточная обработка заняла: {} мс", (endTime - startTime));
            // Пост-обработка (гамма-коррекция)
            log.info(CORRECTION);
            ImageProcessor gammaProcessor = new GammaCorrection(2.2, 1);
            gammaProcessor.process(renderedImageSingleThreaded);
            // Сохранение изображения
            ImageUtils.save(renderedImageSingleThreaded, outputPath, ImageFormat.valueOf(format));
        }
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

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                log.info(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка после nextInt()
                if (value > 0) {
                    return value;
                } else {
                    log.warn("Значение должно быть положительным числом.");
                }
            } catch (Exception e) {
                scanner.nextLine(); // Очистка некорректного ввода
            }
        }
    }

    private static String readImageFormat(Scanner scanner) {
        while (true) {
            log.info("Введите формат изображения (например, PNG):");
            String format = scanner.nextLine().trim().toUpperCase();
            try {
                ImageFormat.valueOf(format);
                return format;
            } catch (IllegalArgumentException e) {
                log.warn("Некорректный формат. Допустимые форматы: PNG, JPEG, BMP.");
            }
        }
    }

    private static int readOneOrTwo(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка после nextInt()
                if (value == 1 || value == 2) {
                    return value;
                } else {
                    log.warn("Значение должно быть: 1 или 2.");
                }
            } catch (Exception e) {
                scanner.nextLine(); // Очистка некорректного ввода
            }
        }
    }

    // Метод для чтения коэффициентов
    private static double[] readCoefficients(Scanner scanner) {
        double[] coefficients = new double[6];
        log.info("Вы хотите ввести свои коэффициенты или оставить стандартные? (1-свои, 2-стандартные)");
        int n = readOneOrTwo(scanner);
        if (n == 1) {
            String[] prompts = {"Введите коэффициент a:", "Введите коэффициент b:", "Введите коэффициент c:",
                "Введите коэффициент d:", "Введите коэффициент e:", "Введите коэффициент f:"};
            for (int i = 0; i < coefficients.length; i++) {
                while (true) {
                    try {
                        log.info(prompts[i]);
                        coefficients[i] = scanner.nextDouble();
                        break;
                    } catch (Exception e) {
                        scanner.nextLine(); // Очистка некорректного ввода
                        log.warn("Введите допустимое числовое значение.");
                    }
                }
            }
            scanner.nextLine(); // Очистка после ввода
        } else {
            for (int i = 0; i < coefficients.length; i++) {
                if (i == 0 || i == 4) {
                    coefficients[i] = 1;
                } else {
                    coefficients[i] = 0;
                }
            }
        }
        return coefficients;
    }
}
