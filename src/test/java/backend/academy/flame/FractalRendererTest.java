package backend.academy.flame;

import backend.academy.flame.entities.Pixel;
import backend.academy.flame.image.FractalRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FractalRendererTest {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ITERATIONS = 1000000;
    private static final int[] TRANSFORMATION_TYPE = {1};

    @Test
    void testCreateFlamePicSingleThreaded() {
        // Создание изображения с использованием однопоточной обработки
        FractalRenderer.createFlamePic(WIDTH, HEIGHT, ITERATIONS, 2, TRANSFORMATION_TYPE);

        // Проверка наличия сохранённого файла
        File outputFile = new File("src/main/resources/pics/output_image.png");
        assertTrue(outputFile.exists(), "Изображение не было сохранено!");
    }

    @Test
    void testCreateFlamePicMultiThreaded() {
        // Создание изображения с использованием многопоточной обработки
        FractalRenderer.createFlamePic(WIDTH, HEIGHT, ITERATIONS, 1, TRANSFORMATION_TYPE);

        // Проверка наличия сохранённого файла
        File outputFile = new File("src/main/resources/pics/output_image.png");
        assertTrue(outputFile.exists(), "Изображение не было сохранено!");
    }

    @Test
    void testImageHasPixels() throws IOException {
        // Создание изображения для теста
        FractalRenderer.createFlamePic(WIDTH, HEIGHT, ITERATIONS, 1, TRANSFORMATION_TYPE);

        // Проверка, что изображение не пустое
        File outputFile = new File("src/main/resources/pics/output_image.png");
        assertTrue(outputFile.exists(), "Изображение не было сохранено!");

        // Проверка, что изображение имеет размер
        assertTrue(outputFile.length() > 0, "Изображение пустое!");

        // Проверка, что изображение можно открыть
        var image = ImageIO.read(outputFile);
        assertTrue(image != null, "Невозможно открыть изображение!");
    }

    @Test
    void testMultiThreadingIsFasterThanSingleThreadingForFlamePicRendering() {
        long startMultiThreaded = System.nanoTime();
        FractalRenderer.createFlamePic(WIDTH, HEIGHT, ITERATIONS, 1, TRANSFORMATION_TYPE);
        long endMultiThreaded = System.nanoTime();
        double durationMultiThreaded = (endMultiThreaded - startMultiThreaded) / 1_000_000_000.0;

        long startSingleThreaded = System.nanoTime();
        FractalRenderer.createFlamePic(WIDTH, HEIGHT, ITERATIONS, 2, TRANSFORMATION_TYPE);
        long endSingleThreaded = System.nanoTime();
        double durationSingleThreaded = (endSingleThreaded - startSingleThreaded) / 1_000_000_000.0;

        assertTrue(durationSingleThreaded > durationMultiThreaded);
    }

}
