package backend.academy.flame.image;

import backend.academy.flame.Checker;
import backend.academy.flame.entities.Pixel;
import backend.academy.flame.entities.Point;
import backend.academy.flame.transformations.CircularTransformation;
import backend.academy.flame.transformations.CrossTransformation;
import backend.academy.flame.transformations.EyefishTransformation;
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
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import static java.lang.Math.log10;

@Slf4j
@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:ParameterAssignment", "checkstyle:ReturnCount",
    "checkstyle:NestedIfDepth"})
public class FractalRenderer {

    private FractalRenderer() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;
    private static Pixel[][] pixels;
    private static BufferedImage image;
    private static Transformation[] linearTransformations;
    private static Scanner scanner = new Scanner(System.in);


    // Однопоточная версия рендеринга
    public static void renderSingleThreaded(int samples, int iterations, int xRes, int yRes, Random random) {
        Transformation transformation = null;

        int tr = Checker.readPositiveInt(scanner, "Введите номер трансформации:");
        linearTransformations = new Transformation[30];
        Arrays.setAll(linearTransformations, i -> getRandomTransformation(tr));
        pixels = new Pixel[xRes][yRes]; // Используем заданные размеры
        for (int i = 0; i < xRes; i++) {
            Arrays.setAll(pixels[i], j -> new Pixel());
        }
        image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, xRes, yRes, processPoint(samples, iterations, xRes, yRes, random), 0, xRes);

        // Применение гамма-коррекции
        applyGammaCorrection(xRes, yRes, 0.8);


        File outputFile = new File("output_image.png");
        try {
            ImageIO.write(image, "png", outputFile);
            log.info("Изображение успешно сохранено: {}", outputFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Ошибка сохранения изображения: {}", e.getMessage());
        }
    }

    // Метод для гамма-коррекции
    private static void applyGammaCorrection(int xRes, int yRes, double gamma) {
        double max = 0.0;

        // Находим максимальное значение нормализованного яркости
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                Pixel pixel = pixels[row][col];
                if (pixel.counter() != 0) {
                    pixel.normal(log10(pixel.counter())); // Применяем нормализацию
                    if (pixel.normal() > max) {
                        max = pixel.normal();
                    }
                }
            }
        }

        // Применяем гамма-коррекцию ко всем пикселям
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                Pixel pixel = pixels[row][col];
                pixel.normal(pixel.normal() / max); // Нормализуем яркость

                // Применяем гамма-коррекцию к цветам
                pixel.red(clamp((int) (pixel.red() * Math.pow(pixel.normal(), 1.0 / gamma))));
                pixel.green(clamp((int) (pixel.green() * Math.pow(pixel.normal(), 1.0 / gamma))));
                pixel.blue(clamp((int) (pixel.blue() * Math.pow(pixel.normal(), 1.0 / gamma))));
            }
        }
    }

    // Метод для ограничения значений цвета в пределах от 0 до 255
    private static int clamp(int value) {
        return Math.min(255, Math.max(0, value));
    }

    // Общая логика обработки точки и обновления пикселя
    private static int[] processPoint(int samples, int iterations, int xRes, int yRes, Random random) {
        int[] intPixel = new int[xRes * yRes];
        double xRange = X_MAX - X_MIN;
        double yRange = Y_MAX - Y_MIN;

        for (int num = 0; num < samples; num++) {
            double newX = X_MIN + xRange * random.nextDouble();
            double newY = Y_MIN + yRange * random.nextDouble();

            for (int step = -20; step < iterations; step++) {
                int i = random.nextInt(linearTransformations.length);
                Point point = linearTransformations[i].apply(newX, newY);

                if (step >= 0 && point.x() >= X_MIN && point.x() <= X_MAX && point.y() >= Y_MIN && point.y() <= Y_MAX) {
                    int x1 = (int) ((point.x() - X_MIN) / xRange * xRes);
                    int y1 = (int) ((point.y() - Y_MIN) / yRange * yRes);

                    if (x1 >= 0 && x1 < xRes && y1 >= 0 && y1 < yRes) {
                        Pixel pixel = pixels[x1][y1];
                        if (!pixel.visited()) {
                            if (linearTransformations[i] instanceof Colorable colorable) {
                                pixel.setColor(colorable.getRed(), colorable.getGreen(), colorable.getBlue());
                            }
                        }
                    }
                }
            }
        }

        for (int y = 0; y < yRes; y++) {
            for (int x = 0; x < xRes; x++) {
                Pixel pixel = pixels[x][y];
                intPixel[y * xRes + x] = (255 << 24) | (pixel.red() << 16) | (pixel.green() << 8) | pixel.blue();
            }
        }
        return intPixel;
    }

    private static Transformation getRandomTransformation(int tr) {

        switch (tr) {
            case 1: return new EyefishTransformation();
            case 2: return new HeartTransformation();
            case 3: return new WavesTransformation();
            case 4: return new TangentTransformation();
            case 5: return new CrossTransformation();
            case 6: return new LinearTransformation();
            case 7: return new CircularTransformation();
            case 8: return new SwirlTransformation();
            case 9: return new SpiralTransformation();
            default: return new LinearTransformation();  // По умолчанию
        }
    }

}
