package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.Getter;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:UniqueProperties"})
@Getter
public class WavesTransformation extends AbstractTransformation {

    @Override
    public Point apply(Point point) {
        Point transformedPoint = transformPoint(point.x(), point.y());
        double newX = transformedPoint.x();
        double newY = transformedPoint.y();
        return new Point(newX, newY);
    }

    @Override public Point apply(double x, double y) {
        Point transformedPoint = transformPoint(x, y);
        double newX = transformedPoint.x();
        double newY = transformedPoint.y();
        // Применяем формулы
        newX = newX + 1 * Math.sin(newY / 0.5 * 0.5);
        newY = newY + 0.7 * Math.sin(newX / 0.3 * 0.3);
        return new Point(newX, newY);
    }
}

