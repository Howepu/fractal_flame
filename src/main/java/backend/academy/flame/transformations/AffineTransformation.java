package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AffineTransformation implements Transformation {
    protected final double a;
    protected final double b;
    protected final double c;
    protected final double d;
    protected final double e;
    protected final double f;

    protected Point applyAffine(Point point) {
        double affineX = a * point.x() + b * point.y() + c;
        double affineY = d * point.x() + e * point.y() + f;
        return new Point(affineX, affineY);
    }

    @Override
    public Point apply(Point point) {
        Point affinePoint = applyAffine(point);
        return transformPoint(affinePoint);
    }

    // Метод для реализации конкретного преобразования
    protected abstract Point transformPoint(Point point);
}
