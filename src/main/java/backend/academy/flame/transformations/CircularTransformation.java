package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;


@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:UniqueProperties"})
public class CircularTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        return apply(point.x(), point.y());
    }

    @Override
    public Point apply(double x, double y) {
        // Логика CircularTransformation
        double transformedX = a() * x + b() * y + c();
        double transformedY = d() * x + e() * y + f();

        double r = Math.sqrt(transformedX * transformedX + transformedY * transformedY);
        double theta = Math.atan2(transformedY, transformedX);

        double newR = r * Math.sin(theta * 3);
        double newTheta = theta + Math.log1p(r);

        double newX = newR * Math.cos(newTheta);
        double newY = newR * Math.sin(newTheta);

        return new Point(newX, newY);
    }
}
