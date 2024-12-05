package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:AvoidNoArgumentSuperConstructorCall")
public class CrossTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        Point transformedPoint = transformPoint(point.x(), point.y());
        double newX = transformedPoint.x();
        double newY = transformedPoint.y();

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        Point transformedPoint = transformPoint(x, y);
        double newX = transformedPoint.x();
        double newY = transformedPoint.y();
        double denominator = Math.pow(newX * newX - newY * newY, 2);
        double n = Math.sqrt(1 / denominator);

        newX = n * newX;
        newY = n * newY;

        return new Point(newX, newY);
    }
}
