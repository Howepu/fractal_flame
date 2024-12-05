package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings({"checkstyle:AvoidNoArgumentSuperConstructorCall", "checkstyle:UniqueProperties"})
public class SpiralTransformation extends AbstractTransformation {

    @Override
    public Point apply(Point point) {
        Point transformedPoint = transformPoint(point.x(), point.y());
        double newX1 = transformedPoint.x();
        double newY2 = transformedPoint.y();
        return new Point(newX1, newY2);
    }

    @Override
    public Point apply(double x, double y) {
        Point transformedPoint = transformPoint(x, y);
        double newX = transformedPoint.x();
        double newY = transformedPoint.y();

        // Рассчитываем радиус и угол
        double r = Math.sqrt(newX * newX + newY * newY);
        double s = Math.atan2(newY, newX);

        // Применяем эффект спирали
        newX = (1 / r) * (Math.cos(s) + Math.sin(r));
        newY = (1 / r) * (Math.sin(s) + Math.cos(r));

        return new Point(newX, newY);
    }
}
