package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import backend.academy.flame.entities.Point;
import backend.academy.flame.entities.Rect;
import backend.academy.flame.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ForkJoinPool;

public class FractalRenderer {
    private static final int NUMBER = 256;
    private static final Random RANDOM = new Random();

    public FractalImage render(FractalImage canvas, Rect world, List<Transformation> transformations,
        int samples, int iterations, int threads) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(threads);
        ExecutorCompletionService<Void> completionService = new ExecutorCompletionService<>(forkJoinPool);

        int samplesPerThread = samples / threads;
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int t = 0; t < threads; t++) {
            tasks.add(() -> {
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
                            int randomR = threadLocalRandom.nextInt(NUMBER);
                            int randomG = threadLocalRandom.nextInt(NUMBER);
                            int randomB = threadLocalRandom.nextInt(NUMBER);
                            Pixel currentPixel = canvas.pixel(x, y);
                            Pixel newPixel = currentPixel.addColor(randomR, randomG, randomB);
                            canvas.setPixel(x, y, newPixel);
                        }
                    }
                }
                return null;
            });
        }

        for (Callable<Void> task : tasks) {
            completionService.submit(task);
        }

        // Ожидание пока все task выполнятся
        for (int t = 0; t < threads; t++) {
            completionService.take();
        }

        forkJoinPool.shutdown();
        return canvas;
    }

    public FractalImage renderSingleThreaded(FractalImage canvas, Rect world, List<Transformation> transformations,
        int samples, int iterations) {
        Point point;
        for (int i = 0; i < samples; i++) {
            point = randomPoint(world);
            for (int j = 0; j < iterations; j++) {
                Transformation transformation = transformations.get(RANDOM.nextInt(transformations.size()));
                point = transformation.apply(point);
                if (!world.contains(point)) {
                    continue;
                }

                int x = (int) ((point.x() - world.x()) / world.width() * canvas.width());
                int y = (int) ((point.y() - world.y()) / world.height() * canvas.height());

                if (canvas.contains(x, y)) {
                    int randomR = RANDOM.nextInt(NUMBER);
                    int randomG = RANDOM.nextInt(NUMBER);
                    int randomB = RANDOM.nextInt(NUMBER);

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
