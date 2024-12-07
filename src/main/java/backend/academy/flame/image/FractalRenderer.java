package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import backend.academy.flame.entities.Point;
import backend.academy.flame.transformations.CircularTransformation;
import backend.academy.flame.transformations.CrossTransformation;
import backend.academy.flame.transformations.EyeFishTransformation;
import backend.academy.flame.transformations.HeartTransformation;
import backend.academy.flame.transformations.LinearTransformation;
import backend.academy.flame.transformations.SpiralTransformation;
import backend.academy.flame.transformations.SwirlTransformation;
import backend.academy.flame.transformations.TangentTransformation;
import backend.academy.flame.transformations.Transformation;
import backend.academy.flame.transformations.WavesTransformation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:MultipleStringLiterals", "checkstyle:NestedIfDepth",
    "Dublicates"})
@Slf4j
public class FractalRenderer {
    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;
    private static final int SAMPLES = 100;
    private static final Random RANDOM = new Random();
    private static Transformation[] linearTransformations;
    private static Pixel[][] pixels;
    private static BufferedImage image;
    private static Scanner scanner = new Scanner(System.in);

    private FractalRenderer() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void createFlamePic(int width, int height, int it, int choice, int[] tr) {
        long startTime = System.nanoTime(); // Замер времени начала

        linearTransformations = new Transformation[SAMPLES];
        Arrays.setAll(linearTransformations, i -> new LinearTransformation());

        pixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            Arrays.setAll(pixels[i], j -> new Pixel());
        }

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int[] rgbData;
        if (choice == 1) {
            rgbData = processPointsMultithreaded(SAMPLES, it, width, height, tr);
        } else {
            rgbData = processPointsSingleThreaded(SAMPLES, it, width, height, tr);
        }

        image.setRGB(0, 0, width, height, rgbData, 0, width);

        File resourcesDir = new File("src/main/resources/pics");
        if (!resourcesDir.exists()) {
            log.info("Папка resources не существует. Создаём её.");
            resourcesDir.mkdirs();
        }

        File outputFile = new File(resourcesDir, "output_image.png");
        try {
            ImageIO.write(image, "png", outputFile);
            log.info("Изображение успешно сохранено: {} ", outputFile.getAbsolutePath());
        } catch (IOException e) {
            log.info("Ошибка сохранения изображения: {} ", e.getMessage());
        }

        long endTime = System.nanoTime(); // Замер времени окончания
        double duration = (endTime - startTime) / 1_000_000_000.0; // Перевод в секунды
        log.info("Время выполнения: {} секунд", duration);
    }

    public static int[] processPointsMultithreaded(int n, int it, int xRes, int yRes, int[] tr) {

        int threadsCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        Pixel[][][] threadPixels = new Pixel[threadsCount][xRes][yRes];
        for (int i = 0; i < threadsCount; i++) {
            for (int x = 0; x < xRes; x++) {
                for (int y = 0; y < yRes; y++) {
                    threadPixels[i][x][y] = new Pixel();
                }
            }
        }

        int blockSize = n / threadsCount;
        List<Future<Void>> futures = new ArrayList<>();

        for (int threadId = 0; threadId < threadsCount; threadId++) {
            final int currentThreadId = threadId;
            int start = threadId * blockSize;
            int end = (threadId == threadsCount - 1) ? n : (threadId + 1) * blockSize;

            futures.add(executor.submit(() -> {
                processPointsWithLocalBuffer(start, end, it, xRes, yRes, tr, threadPixels[currentThreadId]);
                return null;
            }));
        }

        // Ожидаем завершения потоков
        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        }

        executor.shutdown();

        // Объединяем результаты
        mergeThreadPixels(threadPixels, xRes, yRes);

        normalizePixels(xRes, yRes);
        return assembleImageArray(xRes, yRes);
    }


    private static void mergeThreadPixels(Pixel[][][] threadPixels, int xRes, int yRes) {
        for (int x = 0; x < xRes; x++) {
            for (int y = 0; y < yRes; y++) {
                Pixel mergedPixel = pixels[x][y];
                for (Pixel[][] threadBuffer : threadPixels) {
                    Pixel threadPixel = threadBuffer[x][y];
                    if (threadPixel.counter() > 0) {
                        if (mergedPixel.counter() == 0) {
                            mergedPixel.setColor(threadPixel.red(), threadPixel.green(), threadPixel.blue());
                        } else {
                            mergedPixel.setAvg(threadPixel.red(), threadPixel.green(), threadPixel.blue());
                        }
                    }
                }
            }
        }
    }


    public static int[] processPointsSingleThreaded(int n, int it, int xRes, int yRes, int[] tr) {

        for (int num = 0; num < n; num++) {
            processPoints(num, num + 1, it, xRes, yRes, tr);
        }

        normalizePixels(xRes, yRes);

        return assembleImageArray(xRes, yRes);
    }

    private static int[] assembleImageArray(int xRes, int yRes) {
        int[] intPixel = new int[xRes * yRes];
        for (int y = 0; y < yRes; y++) {
            for (int x = 0; x < xRes; x++) {
                Pixel pixel = pixels[x][y];
                intPixel[y * xRes + x] = (255 << 24) | (pixel.red() << 16) | (pixel.green() << 8) | pixel.blue();
            }
        }
        return intPixel;
    }

    private static void processPointsWithBuffer(
        int start, int end, int it, int xRes, int yRes, int[] tr, Pixel[][] pixels, Random random) {
        int numSamplesPerTransformation = SAMPLES / tr.length; // Размер блока для каждой трансформации

        for (int num = start; num < end; num++) {
            for (int step = -20; step < it; step++) {
                // Генерация случайных координат
                double newX = X_MIN + (X_MAX - X_MIN) * random.nextDouble();
                double newY = Y_MIN + (Y_MAX - Y_MIN) * random.nextDouble();
                Point point = applyTransformation(num, newX, newY, tr);

                // Проверка границ и обработка пикселей
                if (step >= 0 && point.x() >= X_MIN && point.x() <= X_MAX
                    && point.y() >= Y_MIN && point.y() <= Y_MAX) {
                    int x1 = (int) (xRes - Math.floor(((X_MAX - point.x()) / (X_MAX - X_MIN)) * xRes));
                    int y1 = (int) (yRes - Math.floor(((Y_MAX - point.y()) / (Y_MAX - Y_MIN)) * yRes));

                    if (x1 >= 0 && x1 < xRes && y1 >= 0 && y1 < yRes) {
                        handlePixel(pixels[x1][y1], num);
                    }
                }
            }
        }
    }

    private static void handlePixel(Pixel pixel, int num) {
        if (pixel.counter() == 0) {
            if (linearTransformations[num] instanceof Colorable colorable) {
                pixel.setColor(colorable.getRed(), colorable.getGreen(), colorable.getBlue());
            }
        } else {
            if (linearTransformations[num] instanceof Colorable colorable) {
                pixel.setAvg(colorable.getRed(), colorable.getGreen(), colorable.getBlue());
            }
        }
    }

    private static void processPointsWithLocalBuffer(
        int start, int end, int it, int xRes, int yRes, int[] tr, Pixel[][] localPixels) {
        processPointsWithBuffer(start, end, it, xRes, yRes, tr, localPixels, ThreadLocalRandom.current());
    }

    private static void processPoints(int start, int end, int it, int xRes, int yRes, int[] tr) {
        processPointsWithBuffer(start, end, it, xRes, yRes, tr, pixels, RANDOM);
    }



    public static void normalizePixels(int xRes, int yRes) {
        double max = 0.0;
        double gamma = 0.8;

        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (pixels[row][col].counter() != 0) {
                    pixels[row][col].normal(log10(pixels[row][col].counter()));
                    if (pixels[row][col].normal() > max) {
                        max = pixels[row][col].normal();
                    }
                }
            }
        }

        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                pixels[row][col].normal(pixels[row][col].normal() / max);
                pixels[row][col].red((int) (pixels[row][col].red() * pow(pixels[row][col].normal(), (1.0 / gamma))));
                pixels[row][col].green(
                    (int) (pixels[row][col].green() * pow(pixels[row][col].normal(), (1.0 / gamma))));
                pixels[row][col].blue((int) (pixels[row][col].blue() * pow(pixels[row][col].normal(), (1.0 / gamma))));
            }
        }
    }

    private static List<Transformation> getTransformations(int[] tr) {
        List<Transformation> transformations = new ArrayList<>();
        for (int t : tr) {
            switch (t) {
                case 1 -> transformations.add(new CircularTransformation());
                case 2 -> transformations.add(new CrossTransformation());
                case 3 -> transformations.add(new EyeFishTransformation());
                case 4 -> transformations.add(new HeartTransformation());
                case 5 -> transformations.add(new SpiralTransformation());
                case 6 -> transformations.add(new SwirlTransformation());
                case 7 -> transformations.add(new TangentTransformation());
                case 8 -> transformations.add(new WavesTransformation());
                default -> throw new IllegalArgumentException("Неизвестная трансформация: " + t);
            }
        }
        return transformations;
    }

    private static Point applyTransformation(int num, double newX, double newY, int[] tr) {
        // Применяем линейную трансформацию
        Point point = linearTransformations[num].apply(newX, newY);

        // Определяем, какую трансформацию применять
        int numSamplesPerTransformation = SAMPLES / tr.length;
        int transformationIndex = num / numSamplesPerTransformation;
        if (transformationIndex < tr.length) {
            Transformation transformation = getTransformations(tr).get(transformationIndex);
            point = transformation.apply(point.x(), point.y());
        }

        return point;
    }

}
