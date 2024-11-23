package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double s = Math.atan(point.x() / point.y());
        double newX = r * Math.sin(s * r);
        double newY = -(r * Math.cos(s * r));
        return new Point(newX, newY);
    }
}
