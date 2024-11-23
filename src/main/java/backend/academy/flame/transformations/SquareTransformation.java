package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class SquareTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double n = Math.sqrt(1 / Math.pow((Math.pow(point.x(), 2) - Math.pow(point.y(), 2)), 2));
        double newX = n * point.x();
        double newY = n * point.y();
        return new Point(newX, newY);
    }
}
