package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class EyefishTransformation extends AffineTransformation {

    // Конструктор, который передает параметры в конструктор родительского класса
    public EyefishTransformation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        // Применяем аффинное преобразование
        double affineX = point.x();
        double affineY = point.y();

        // Расчёт радиуса
        double r = Math.sqrt(affineX * affineX + affineY * affineY);

        // Применяем искажение (эффект рыбьего глаза)
        double newX = (2 / (r + 1)) * affineX;
        double newY = (2 / (r + 1)) * affineY;

        // Возвращаем новую точку после применения эффекта
        return new Point(newX, newY);
    }
}
