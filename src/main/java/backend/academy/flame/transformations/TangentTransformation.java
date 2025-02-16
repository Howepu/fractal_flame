package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings({"checkstyle:AvoidNoArgumentSuperConstructorCall", "checkstyle:UniqueProperties"})
public class TangentTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = point.x();
        double newY = point.y();
        newX = Math.sin(newX) / Math.cos(newY);
        newY = Math.tan(newY);
        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        double newX = x;
        double newY = y;
        newX = Math.sin(newX) / Math.cos(newY);
        newY = Math.tan(newY);
        return new Point(newX, newY);
    }
}
