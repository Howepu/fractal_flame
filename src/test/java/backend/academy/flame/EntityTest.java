package backend.academy.flame;

import backend.academy.flame.entities.Pixel;
import backend.academy.flame.entities.Point;
import backend.academy.flame.entities.Rect;
import backend.academy.flame.image.FractalImage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    void testPixelAddColor() {
        Pixel pixel = new Pixel(100, 100, 100, 1);
        Pixel newPixel = pixel.addColor(50, 20, 90);
        assertEquals(150, newPixel.r());
        assertEquals(120, newPixel.g());
        assertEquals(190, newPixel.b());
        assertEquals(2, newPixel.hitCount());
    }

    @Test
    void testRectContains() {
        Rect rect = new Rect(0, 0, 100, 100);
        Point inside = new Point(50, 50);
        Point outside = new Point(200, 200);
        assertTrue(rect.contains(inside));
        assertFalse(rect.contains(outside));
    }

    @Test
    void testFractalImageCreation() {
        FractalImage image = FractalImage.create(100, 100);
        assertEquals(100, image.width());
        assertEquals(100, image.height());
        assertNotNull(image.pixel(0, 0));
    }
}
