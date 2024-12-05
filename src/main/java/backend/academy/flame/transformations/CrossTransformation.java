package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class CrossTransformation extends AbstractTransformation {
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
        double denominator = Math.pow(newX * newX - newY * newY, 2);
        double n = Math.sqrt(1 / denominator);

        newX = n * newX;
        newY = n * newY;

        return new Point(newX, newY);
    }
}
