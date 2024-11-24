package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GammaCorrection implements ImageProcessor {
    private final double gamma;
    private final double brightnessFactor;
    private static final double MAX_COLOR_VALUE = 255.0;

    @Override
    public void process(FractalImage image) {
        for (int i = 0; i < image.data().length; i++) {
            Pixel pixel = image.data()[i];
            int correctedR = correctColor(pixel.r());
            int correctedG = correctColor(pixel.g());
            int correctedB = correctColor(pixel.b());
            image.data()[i] = new Pixel(correctedR, correctedG, correctedB, pixel.hitCount());
        }
    }

    // Логарифмическая гамма-коррекция
    private int correctColor(int color) {
        double normalized = color / MAX_COLOR_VALUE; // Нормализация
        double gammaCorrected = Math.pow(normalized, gamma);
        double logCorrected = Math.log(1 + gammaCorrected * MAX_COLOR_VALUE) / Math.log(1 + MAX_COLOR_VALUE);
        return (int) Math.min(MAX_COLOR_VALUE, logCorrected * brightnessFactor * MAX_COLOR_VALUE);
    }
}

