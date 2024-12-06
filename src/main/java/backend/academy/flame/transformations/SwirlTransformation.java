package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:UniqueProperties")
public class SwirlTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = point.x();
        double newY = point.y();

        double r = Math.sqrt(newX * newX + newY * newY);

        newX = newX * Math.sin(r * r) - newY * Math.cos(r * r);
        newY = newX * Math.cos(r * r) + newY * Math.sin(r * r);

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        double newX = x;
        double newY = y;

        double r = Math.sqrt(newX * newX + newY * newY);

        newX = newX * Math.sin(r * r) - newY * Math.cos(r * r);
        newY = newX * Math.cos(r * r) + newY * Math.sin(r * r);

        return new Point(newX, newY);
    }
}
