package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:AvoidNoArgumentSuperConstructorCall")
public class TangentTransformation extends AbstractTransformation {

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
        newX = Math.sin(newX) / Math.cos(newY);
        newY = Math.tan(newY);
        return new Point(newX, newY);
    }
}
