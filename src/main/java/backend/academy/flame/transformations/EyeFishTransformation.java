package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:AvoidNoArgumentSuperConstructorCall")
public class EyeFishTransformation extends AbstractTransformation {

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
        double r = Math.sqrt(newX * newX + newY * newY);

        // Apply eyefish distortion
        newX = (2 / (r + 1)) * newX;
        newY = (2 / (r + 1)) * newY;

        return new Point(newX, newY);
    }
}
