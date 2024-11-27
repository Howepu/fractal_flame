package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import backend.academy.flame.entities.Point;
import backend.academy.flame.entities.Rect;
import backend.academy.flame.transformations.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ForkJoinPool;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:ParameterAssignment"})
public class FractalRenderer {
    private static final Random RANDOM = new Random();
    private static final int PIXEL = 256;
    private static final int NUMBER = 100;

    // Многопоточная версия рендеринга
    public FractalImage renderMultithreaded(FractalImage canvas, Rect world, List<Transformation> transformations,
        int samples, int iterations, int threads) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(threads);
        ExecutorCompletionService<Void> completionService = new ExecutorCompletionService<>(forkJoinPool);

        int samplesPerThread = samples / threads;
        for (int t = 0; t < threads; t++) {
            completionService.submit(() -> {
                Random threadLocalRandom = new Random();
                Point point;
                for (int i = 0; i < samplesPerThread; i++) {
                    point = randomPoint(world);
                    processPoint(canvas, world, transformations, point, threadLocalRandom, iterations);
                }
                return null;
            });
        }

        for (int t = 0; t < threads; t++) {
            completionService.take();
        }

        forkJoinPool.shutdown();
        return canvas;
    }

    // Однопоточная версия рендеринга
    public FractalImage renderSingleThreaded(FractalImage canvas, Rect world, List<Transformation> transformations,
        int samples, int iterations) {
        for (int i = 0; i < samples; i++) {
            Point point = randomPoint(world);
            processPoint(canvas, world, transformations, point, RANDOM, iterations);
        }
        return canvas;
    }

    // Общая логика обработки точки и обновления пикселя
    private void processPoint(FractalImage canvas, Rect world, List<Transformation> transformations, Point point,
        Random random, int iterations) {
        for (int j = 20; j < iterations; j++) {
            Transformation transformation = transformations.get(random.nextInt(transformations.size()));
            point = transformation.apply(point);
            if (!world.contains(point)) {
                continue;
            }

            // Корректное преобразование координат
            int x = (int) ((point.x() - world.x()) / world.width() * canvas.width());
            int y = (int) ((point.y() - world.y()) / world.height() * canvas.height());

            if (canvas.contains(x, y)) {
                // Плавное изменение цвета с учётом количества попаданий
                int baseColorR = (int) (Math.min(PIXEL, point.x() * NUMBER));
                int baseColorG = (int) (Math.min(PIXEL, point.y() * NUMBER));
                int baseColorB = PIXEL - (int) (Math.min(PIXEL, (point.x() + point.y()) * 50));

                Pixel currentPixel = canvas.pixel(x, y);

                // Смешиваем текущий цвет с новым для создания плавных переходов
                Pixel newPixel = currentPixel.addColor(baseColorR, baseColorG, baseColorB);

                int additionalBrightness = Math.min(PIXEL, currentPixel.hitCount() * 10);
                newPixel = new Pixel(
                    Math.min(PIXEL, newPixel.r() + additionalBrightness),
                    Math.min(PIXEL, newPixel.g() + additionalBrightness),
                    Math.min(PIXEL, newPixel.b() + additionalBrightness),
                    newPixel.hitCount()
                );

                canvas.setPixel(x, y, newPixel);
            }
        }
    }

    private Point randomPoint(Rect world) {
        double x = world.x() + RANDOM.nextDouble() * world.width();
        double y = world.y() + RANDOM.nextDouble() * world.height();
        return new Point(x, y);
    }
}
