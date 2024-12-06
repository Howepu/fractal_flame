package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;


@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:UniqueProperties"})
public class CircularTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());

        double newR = r * Math.sin(theta * 3);
        double newTheta = theta + Math.log1p(r);

        double newX = newR * Math.cos(newTheta);
        double newY = newR * Math.sin(newTheta);

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        double newR = r * Math.sin(theta * 3);
        double newTheta = theta + Math.log1p(r);

        double newX = newR * Math.cos(newTheta);
        double newY = newR * Math.sin(newTheta);

        return new Point(newX, newY);
    }
}
