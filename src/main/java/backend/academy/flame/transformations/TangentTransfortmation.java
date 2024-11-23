package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class TangentTransfortmation implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = Math.sin(point.x()) / Math.cos(point.y());
        double newY = Math.tan(point.y());
        return new Point(newX, newY);
    }
}
