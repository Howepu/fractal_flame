package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EyefishTransformation implements Transformation {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    @Override
    public Point apply(Point point) {
        double affineX = a * point.x() + b * point.y() + c; // 2
        double affineY = d * point.x() + e * point.y() + f; // 3
        double r = Math.sqrt(affineX * affineX + affineY * affineY); //3,6
        double newX = (2 / (r + 1)) * affineX; // 0,87
        double newY = (2 / (r + 1)) * affineY; // 1,3
        return new Point(newX, newY);
    }
}
