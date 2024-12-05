package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;


public class HeartTransformation extends AbstractTransformation {
    @Override
    public Point apply(Point point) {
        double x = a() * point.x() + b() * point.y() + c();
        double y = d() * point.x() + e() * point.y() + f();
        return new Point(x, y);
    }

    @Override
    public Point apply(double x, double y) {
        double newX = a() * x + b() * y + c();
        double newY = d() * x + e() * y + f();
        double r = Math.sqrt(newX * newX + newY * newY);
        double theta = Math.atan(newX / newY);
        newX = r * Math.sin(theta * r);
        newY = -r * Math.cos(theta * r);
        return new Point(newX, newY);
    }
}


