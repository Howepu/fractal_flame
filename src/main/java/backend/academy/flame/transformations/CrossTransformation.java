package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class CrossTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double n = Math.sqrt(1 / Math.pow(point.x() * point.x() - point.y() * point.y(), 2));

        double newX = n * point.x();
        double newY = n * point.y();

        return new Point(newX, newY);
    }
}
