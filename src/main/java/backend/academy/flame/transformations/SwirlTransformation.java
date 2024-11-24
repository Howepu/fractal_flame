package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class SwirlTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double newX = point.x() * Math.sin(Math.pow(r, 2)) - point.y() * Math.cos(Math.pow(r, 2));
        double newY = point.x() * Math.cos(Math.pow(r, 2)) + point.y() * Math.sin(Math.pow(r, 2));
        return new Point(newX, newY);


    }
}
