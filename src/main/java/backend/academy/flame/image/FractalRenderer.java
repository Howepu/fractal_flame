package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import backend.academy.flame.entities.Point;
import backend.academy.flame.entities.Rect;
import backend.academy.flame.transformations.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ForkJoinPool;


public class FractalRenderer {
    private static final int NUMBER_1 = 200;
    private static final int NUMBER_2 = 200;
    private static final Random RANDOM = new Random();

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
                    for (int j = 0; j < iterations; j++) {
                        Transformation transformation = transformations
                            .get(threadLocalRandom.nextInt(transformations.size()));
                        point = transformation.apply(point);
                        if (!world.contains(point)) {
                            continue;
                        }

                        int x = (int) ((point.x() - world.x()) / world.width() * canvas.width());
                        int y = (int) ((point.y() - world.y()) / world.height() * canvas.height());

                        if (canvas.contains(x, y)) {
                            int randomR = threadLocalRandom.nextInt(NUMBER_1) + NUMBER_2;
                            int randomG = threadLocalRandom.nextInt(NUMBER_1) + NUMBER_2;
                            int randomB = threadLocalRandom.nextInt(NUMBER_1) + NUMBER_2;

                            Pixel currentPixel = canvas.pixel(x, y);
                            Pixel newPixel = currentPixel.addColor(randomR, randomG, randomB);
                            canvas.setPixel(x, y, newPixel);
                        }
                    }
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
            for (int j = 0; j < iterations; j++) {
                Transformation transformation = transformations.get(RANDOM.nextInt(transformations.size()));
                point = transformation.apply(point);
                if (!world.contains(point)) {
                    continue;
                }

                int x = (int) ((point.x() - world.x()) / world.width() * canvas.width());
                int y = (int) ((point.y() - world.y()) / world.height() * canvas.height());

                if (canvas.contains(x, y)) {
                    int randomR = RANDOM.nextInt(NUMBER_1) + NUMBER_2;
                    int randomG = RANDOM.nextInt(NUMBER_1) + NUMBER_2;
                    int randomB = RANDOM.nextInt(NUMBER_1) + NUMBER_2;

                    Pixel currentPixel = canvas.pixel(x, y);
                    Pixel newPixel = currentPixel.addColor(randomR, randomG, randomB);
                    canvas.setPixel(x, y, newPixel);
                }
            }
        }
        return canvas;
    }

    private Point randomPoint(Rect world) {
        double x = world.x() + RANDOM.nextDouble() * world.width();
        double y = world.y() + RANDOM.nextDouble() * world.height();
        return new Point(x, y);
    }
}
