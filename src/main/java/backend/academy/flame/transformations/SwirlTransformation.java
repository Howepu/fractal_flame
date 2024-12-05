package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:AvoidNoArgumentSuperConstructorCall")
public class SwirlTransformation extends AbstractTransformation {

    public SwirlTransformation() {
        super();
    }

    @Override
    public Point apply(Point point) {
        // Выполняем аффинное преобразование
        double x = a() * point.x() + b() * point.y() + c();
        double y = d() * point.x() + e() * point.y() + f();

        // Рассчитываем радиус
        double r = Math.sqrt(x * x + y * y);

        // Применяем эффект вихря
        double newX = x * Math.sin(r * r) - y * Math.cos(r * r);
        double newY = x * Math.cos(r * r) + y * Math.sin(r * r);

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        // Выполняем аффинное преобразование
        double affineX = a() * x + b() * y + c();
        double affineY = d() * x + e() * y + f();

        // Рассчитываем радиус
        double r = Math.sqrt(affineX * affineX + affineY * affineY);

        // Применяем эффект вихря
        double newX = affineX * Math.sin(r * r) - affineY * Math.cos(r * r);
        double newY = affineX * Math.cos(r * r) + affineY * Math.sin(r * r);

        return new Point(newX, newY);
    }
}
