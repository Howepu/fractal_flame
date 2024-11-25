package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HyperbolicTransformation implements Transformation {
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
        double s = (affineY != 0) ? Math.atan(affineX / affineY) : 0;
        double newX = Math.sin(s) / r;
        double newY = r * Math.cos(s);
        return new Point(newX, newY);
    }
}
