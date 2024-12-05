package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("PMD.CPD")
public class HeartTransformation extends AbstractTransformation {

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
        double theta = Math.atan(newX / newY);
        newX = r * Math.sin(theta * r);
        newY = -r * Math.cos(theta * r);
        return new Point(newX, newY);
    }
}


