package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CrossTransformation implements Transformation {
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
        double n = Math.sqrt(1 / Math.pow(affineX * affineX - affineY * affineY, 2));

        double newX = n * affineX;
        double newY = n * affineY;

        return new Point(newX, newY);
    }
}
