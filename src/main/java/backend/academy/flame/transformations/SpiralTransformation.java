package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class SpiralTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double s = Math.atan(point.x() / point.y());
        double newX = 1 / r * (Math.cos(s) + Math.sin(r));
        double newY = 1 / r * (Math.sin(s) + Math.cos(r));
        return new Point(newX, newY);
    }
}
