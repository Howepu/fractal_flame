package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class CrossTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        // Вычисляем знаменатель
        double denominator = Math.pow(point.x() * point.x() - point.y() * point.y(), 2);
        if (denominator == 0) {
            return new Point(0, 0);
        }

        // Вычисляем масштабирующий фактор
        double factor = Math.sqrt(1 / denominator);

        // Новые координаты
        double newX = factor * point.x();
        double newY = factor * point.y();

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        // Вычисляем знаменатель
        double denominator = Math.pow(x * x - y * y, 2);
        if (denominator == 0) {
            return new Point(0, 0);
        }

        // Вычисляем масштабирующий фактор
        double factor = Math.sqrt(1 / denominator);

        // Новые координаты
        double newX = factor * x;
        double newY = factor * y;

        return new Point(newX, newY);
    }
}
