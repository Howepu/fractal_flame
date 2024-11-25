package backend.academy.flame;

import backend.academy.flame.entities.Rect;
import backend.academy.flame.image.FractalImage;
import backend.academy.flame.image.FractalRenderer;
import backend.academy.flame.transformations.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FractalRendererTest {

    @Test
    void testRenderPerformance() throws InterruptedException {
        int width = 1920;
        int height = 1080;
        int samples = 10000;
        int iterations = 1000;
        int threads = 4;

        Rect world = new Rect(-1, -1, 2, 2);
        FractalImage canvasSingle = FractalImage.create(width, height);
        FractalImage canvasMulti = FractalImage.create(width, height);

        List<Transformation> transformations = List.of(
            new SwirlTransformation(1, 0, 0, 0, 1, 0),
            new HeartTransformation(1, 0, 0, 0, 1, 0),
            new EyefishTransformation(1, 0, 0, 0, 1, 0)
        );

        FractalRenderer renderer = new FractalRenderer();

        long startSingle = System.nanoTime();
        renderer.renderSingleThreaded(canvasSingle, world, transformations, samples, iterations);
        long durationSingle = System.nanoTime() - startSingle;

        long startMulti = System.nanoTime();
        renderer.renderMultithreaded(canvasMulti, world, transformations, samples, iterations, threads);
        long durationMulti = System.nanoTime() - startMulti;

        System.out.printf("Однопоточная обработка: %d ms%n", durationSingle / 1000000);
        System.out.printf("Многопоточная обработка: %d ms%n", durationMulti / 1000000);

        assertTrue(durationMulti < durationSingle, "Многопоточная обработка должна быть быстрее");
    }
}

