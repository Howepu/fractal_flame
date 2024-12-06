package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;


@SuppressWarnings({"checkstyle:AvoidNoArgumentSuperConstructorCall", "checkstyle:UniqueProperties"})
public class EyeFishTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = point.x();
        double newY = point.y();
        double r = Math.sqrt(newX * newX + newY * newY);

        newX = (2 / (r + 1)) * newX;
        newY = (2 / (r + 1)) * newY;

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        double newX = x;
        double newY = y;
        double r = Math.sqrt(newX * newX + newY * newY);

        newX = (2 / (r + 1)) * newX;
        newY = (2 / (r + 1)) * newY;

        return new Point(newX, newY);
    }
}
