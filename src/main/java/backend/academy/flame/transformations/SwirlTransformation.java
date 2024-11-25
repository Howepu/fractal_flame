package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SwirlTransformation implements Transformation {
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
        double newX = affineX * Math.sin(Math.pow(r, 2)) - affineY * Math.cos(Math.pow(r, 2));
        double newY = affineX * Math.cos(Math.pow(r, 2)) + affineY * Math.sin(Math.pow(r, 2));
        return new Point(newX, newY);


    }
}
