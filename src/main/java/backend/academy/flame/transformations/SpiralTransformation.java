package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:AvoidNoArgumentSuperConstructorCall")
public class SpiralTransformation extends AbstractTransformation {

    public SpiralTransformation() {
        super();
    }

    @Override
    public Point apply(Point point) {
        // Выполняем аффинное преобразование
        double x = a() * point.x() + b() * point.y() + c();
        double y = d() * point.x() + e() * point.y() + f();

        // Рассчитываем радиус и угол
        double r = Math.sqrt(x * x + y * y);
        double s = Math.atan2(y, x); // Используем atan2 для корректного вычисления угла

        // Применяем эффект спирали
        double newX = (1 / r) * (Math.cos(s) + Math.sin(r));
        double newY = (1 / r) * (Math.sin(s) + Math.cos(r));

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        // Выполняем аффинное преобразование
        double affineX = a() * x + b() * y + c();
        double affineY = d() * x + e() * y + f();

        // Рассчитываем радиус и угол
        double r = Math.sqrt(affineX * affineX + affineY * affineY);
        double s = Math.atan2(affineY, affineX); // Используем atan2 для корректного вычисления угла

        // Применяем эффект спирали
        double newX = (1 / r) * (Math.cos(s) + Math.sin(r));
        double newY = (1 / r) * (Math.sin(s) + Math.cos(r));

        return new Point(newX, newY);
    }
}
