//package backend.academy.flame;
//
//import backend.academy.flame.image.FractalRenderer;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class FractalRendererTest {
//
//    private static final int WIDTH = 1920; // Пример ширины изображения
//    private static final int HEIGHT = 1080; // Пример высоты изображения
//    private static final int ITERATIONS = 1_000_000; // Пример количества итераций
//    private static final int RUNS = 5; // Количество запусков для усреднения времени выполнения
//    private static final int SAMPLES = 30;
//
//    @Test
//    void testSingleThreadedPerformance() {
//        double averageTime = measureAverageExecutionTime(() ->
//            FractalRenderer.processPointsSingleThreaded(SAMPLES, ITERATIONS, WIDTH, HEIGHT), RUNS);
//
//        System.out.println("Среднее время выполнения (однопоточная версия): " + averageTime + " секунд");
//        assertTrue(averageTime > 0, "Время выполнения должно быть больше нуля");
//    }
//
//    @Test
//    void testMultiThreadedPerformance() {
//        double averageTime = measureAverageExecutionTime(() ->
//            FractalRenderer.processPointsMultithreaded(SAMPLES, ITERATIONS, WIDTH, HEIGHT), RUNS);
//
//        System.out.println("Среднее время выполнения (многопоточная версия): " + averageTime + " секунд");
//        assertTrue(averageTime > 0, "Время выполнения должно быть больше нуля");
//    }
//
//    @Test
//    void testPerformanceComparison() {
//        double singleThreadedTime = measureAverageExecutionTime(() ->
//            FractalRenderer.processPointsSingleThreaded(SAMPLES, ITERATIONS, WIDTH, HEIGHT), RUNS);
//
//        double multiThreadedTime = measureAverageExecutionTime(() ->
//            FractalRenderer.processPointsMultithreaded(SAMPLES, ITERATIONS, WIDTH, HEIGHT), RUNS);
//
//        System.out.println("Однопоточная версия: " + singleThreadedTime + " секунд");
//        System.out.println("Многопоточная версия: " + multiThreadedTime + " секунд");
//
//        assertTrue(multiThreadedTime < singleThreadedTime, "Многопоточная версия должна работать быстрее однопоточной");
//    }
//
//    private double measureAverageExecutionTime(Runnable task, int runs) {
//        double totalTime = 0;
//
//        for (int i = 0; i < runs; i++) {
//            long startTime = System.nanoTime();
//            task.run();
//            long endTime = System.nanoTime();
//
//            totalTime += (endTime - startTime) / 1_000_000_000.0; // Перевод в секунды
//        }
//
//        return totalTime / runs;
//    }
//}
