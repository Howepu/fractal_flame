package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SuppressWarnings("checkstyle:MagicNumber")
public class WavesTransformation implements Transformation {

    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    @Override
    public Point apply(Point point) {
        double affineX = a * point.x() + b * point.y() + c;
        double affineY = d * point.x() + e * point.y() + f;

        // Применяем формулы
        double newX = affineX + 1 * Math.sin(affineY / 0.5 * 0.5);
        double newY = affineY + 0.7 * Math.sin(affineX / 0.3 * 0.3);

        return new Point(newX, newY);
    }
}
