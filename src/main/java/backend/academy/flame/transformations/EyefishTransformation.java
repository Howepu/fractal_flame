package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class EyefishTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double newX = (2 / (r + 1)) * point.x();
        double newY = (2 / (r + 1)) * point.y();
        return new Point(newX, newY);
    }
}
