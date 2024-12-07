package backend.academy.flame;

import backend.academy.flame.image.FractalRenderer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
public class FractalApp {
    private static final int THREADS = Runtime.getRuntime().availableProcessors();


    private FractalApp() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void main(String[] args) throws Exception {
        logSystemConfiguration(); // Вывод конфигурации системы

        log.info("Используется {} потока", THREADS);

        Scanner scanner = new Scanner(System.in);
        Path outputPath = Paths.get("fractal_flame");

        // Проверка ширины
        int width = Checker.readPositiveInt(scanner, "Введите ширину изображения (рекомендовано 1920):");

        // Проверка высоты
        int height = Checker.readPositiveInt(scanner, "Введите высоту изображения (рекомендовано 1080):");

        // Проверка количества итераций
        int iterations = Checker.readPositiveInt(scanner, "Введите количество итераций (рекомендовано 1_000_000):");

        log.info("Выберите режим обработки (1 - многопоточный, 2 - однопоточный):");
        int choice = Checker.readOneOrTwo(scanner);

        int[] tr = Checker.getInputArrayFromConsole();

        FractalRenderer.createFlamePic(width, height, iterations, choice, tr);

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
