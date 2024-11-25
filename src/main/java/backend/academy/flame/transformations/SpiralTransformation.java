package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpiralTransformation implements Transformation {
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
        double r = Math.sqrt(affineX * affineX + affineY * affineY);
        double s = Math.atan(affineX / affineY);
        double newX = 1 / r * (Math.cos(s) + Math.sin(r));
        double newY = 1 / r * (Math.sin(s) + Math.cos(r));
        return new Point(newX, newY);
    }
}
