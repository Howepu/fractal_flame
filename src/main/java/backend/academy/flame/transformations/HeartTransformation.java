package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        // Вычисляем радиус
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());

        // Арктангенс (угол)
        double theta = Math.atan2(point.y(), point.x()); // atan2 лучше, чтобы избежать деления на 0

        // Новые координаты
        double newX = r * Math.sin(r * Math.PI * theta);
        double newY = -r * Math.cos(r * Math.PI * theta);

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        // Вычисляем радиус
        double r = Math.sqrt(x * x + y * y);

        // Арктангенс (угол)
        double theta = Math.atan2(y, x); // atan2 лучше, чтобы избежать деления на 0

        // Новые координаты
        double newX = r * Math.sin(r * Math.PI * theta);
        double newY = -r * Math.cos(r * Math.PI * theta);

        return new Point(newX, newY);
    }
}
