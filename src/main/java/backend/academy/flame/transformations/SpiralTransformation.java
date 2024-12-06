package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings({"checkstyle:AvoidNoArgumentSuperConstructorCall", "checkstyle:UniqueProperties"})
public class SpiralTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = point.x();
        double newY = point.y();

        // Рассчитываем радиус и угол
        double r = Math.sqrt(newX * newX + newY * newY);
        double s = Math.atan2(newY, newX);

        // Применяем эффект спирали
        newX = (1 / r) * (Math.cos(s) + Math.sin(r));
        newY = (1 / r) * (Math.sin(s) + Math.cos(r));

        return new Point(newX, newY);
    }

    @Override
    public Point apply(double x, double y) {
        double newX = x;
        double newY = y;

        // Рассчитываем радиус и угол
        double r = Math.sqrt(newX * newX + newY * newY);
        double s = Math.atan2(newY, newX);

        // Применяем эффект спирали
        newX = (1 / r) * (Math.cos(s) + Math.sin(r));
        newY = (1 / r) * (Math.sin(s) + Math.cos(r));

        return new Point(newX, newY);
    }
}
