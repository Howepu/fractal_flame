package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class SwirlTransformation extends AbstractTransformation {

    @Override
    public Point apply(Point point) {
        Point transformedPoint = transformPoint(point.x(), point.y());
        double newX2 = transformedPoint.x();
        double newY2 = transformedPoint.y();
        return new Point(newX2, newY2);
    }

    @Override
    public Point apply(double x, double y) {
        Point transformedPoint = transformPoint(x, y);
        double newX = transformedPoint.x();
        double newY = transformedPoint.y();

        double r = Math.sqrt(newX * newX + newY * newY);

        newX = newX * Math.sin(r * r) - newY * Math.cos(r * r);
        newY = newX * Math.cos(r * r) + newY * Math.sin(r * r);

        return new Point(newX, newY);
    }
}
