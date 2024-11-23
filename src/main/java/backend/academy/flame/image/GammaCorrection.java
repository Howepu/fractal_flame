package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GammaCorrection implements ImageProcessor {
    private final double gamma;
    private static final int NUMBER = 255;

    @Override
    public void process(FractalImage image) {
        for (int i = 0; i < image.data().length; i++) {
            Pixel pixel = image.data()[i];
            int correctedR = (int) (Math.pow(pixel.r() / NUMBER, gamma) * NUMBER);
            int correctedG = (int) (Math.pow(pixel.g() / NUMBER, gamma) * NUMBER);
            int correctedB = (int) (Math.pow(pixel.b() / NUMBER, gamma) * NUMBER);
            image.data()[i] = new Pixel(correctedR, correctedG, correctedB, pixel.hitCount());
        }
    }
}

