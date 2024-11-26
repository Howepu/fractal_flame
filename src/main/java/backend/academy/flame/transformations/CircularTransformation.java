package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@SuppressWarnings("checkstyle:MagicNumber")
@AllArgsConstructor
public class CircularTransformation implements Transformation {
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
        double theta = Math.atan2(affineY, affineX);

        double newR = r * Math.sin(theta * 3);
        double newTheta = theta + Math.log(r);

        double newX = newR * Math.cos(newTheta);
        double newY = newR * Math.sin(newTheta);

        return new Point(newX, newY);
    }
}
