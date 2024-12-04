//package backend.academy.flame;
//
//import backend.academy.flame.entities.Pixel;
//import backend.academy.flame.image.FractalImage;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GammaCorrectionTest {
//
//    @Test
//    void testGammaCorrection() {
//        FractalImage image = FractalImage.create(2, 2);
//        image.setPixel(0, 0, new Pixel(100, 100, 100, 1));
//        image.setPixel(1, 0, new Pixel(200, 200, 200, 1));
//
//        GammaCorrection gammaCorrection = new GammaCorrection(2.2, 1.0);
//        gammaCorrection.process(image);
//
//        Pixel correctedPixel = image.pixel(0, 0);
//        assertTrue(correctedPixel.r() <= 255 && correctedPixel.r() >= 0);
//    }
//}
//
