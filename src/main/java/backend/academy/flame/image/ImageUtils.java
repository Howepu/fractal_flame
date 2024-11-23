package backend.academy.flame.image;

import backend.academy.flame.entities.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

@SuppressWarnings("checkstyle:MagicNumber")
public final class ImageUtils {
    private ImageUtils() {}

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                int color = (pixel.r() << 16) | (pixel.g() << 8) | pixel.b();
                bufferedImage.setRGB(x, y, color);
            }
        }

        ImageIO.write(bufferedImage, format.name().toLowerCase(), filename.toFile());
    }
}
