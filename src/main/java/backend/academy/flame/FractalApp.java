package backend.academy.flame;

import backend.academy.flame.image.FractalRenderer;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
@UtilityClass
public class FractalApp {
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        logSystemConfiguration(); // Вывод конфигурации системы

        log.info("Используется {} потока", THREADS);

        Scanner scanner = new Scanner(System.in);


        // Проверка ширины
        int width = InputValidator.readPositiveInt(scanner, "Введите ширину изображения (рекомендовано 1920):");

        // Проверка высоты
        int height = InputValidator.readPositiveInt(scanner, "Введите высоту изображения (рекомендовано 1080):");

        // Проверка количества итераций
        int iterations = InputValidator.readPositiveInt(scanner,
            "Введите количество итераций (рекомендовано 1_000_000):");

        log.info("Выберите режим обработки (1 - многопоточный, 2 - однопоточный):");
        int choice = InputValidator.readOneOrTwo(scanner);

        int[] tr = InputValidator.getInputArrayFromConsole(scanner);

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
