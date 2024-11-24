package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:MagicNumber")
public class WavesTransformation implements Transformation {

    @Override
    public Point apply(Point point) {

        double x = point.x();
        double y = point.y();

        // Применяем формулы
        double newX = x + 1 * Math.sin(y * 0.5);
        double newY = y + 0.7 * Math.sin(x * 0.3);

        return new Point(newX, newY);
    }
}
