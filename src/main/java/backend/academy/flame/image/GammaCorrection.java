package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GammaCorrection implements ImageProcessor {
    private final double gamma;
    private final double brightnessFactor; // Новый параметр для усиления яркости
    private final double number = 255;

    @Override
    public void process(FractalImage image) {
        for (int i = 0; i < image.data().length; i++) {
            Pixel pixel = image.data()[i];
            int correctedR = (int) (Math.pow(pixel.r() / number, gamma) * number * brightnessFactor);
            int correctedG = (int) (Math.pow(pixel.g() / number, gamma) * number * brightnessFactor);
            int correctedB = (int) (Math.pow(pixel.b() / number, gamma) * number * brightnessFactor);
            image.data()[i] = new Pixel(correctedR, correctedG, correctedB, pixel.hitCount());
        }
    }
}
