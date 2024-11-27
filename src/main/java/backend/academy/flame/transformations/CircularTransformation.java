package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class CircularTransformation extends AffineTransformation {

    public CircularTransformation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        // Входные координаты после аффинного преобразования
        double affineX = point.x();
        double affineY = point.y();

        // Преобразование в полярные координаты
        double r = Math.sqrt(affineX * affineX + affineY * affineY);
        double theta = Math.atan2(affineY, affineX);

        // Уникальная логика CircularTransformation
        double newR = r * Math.sin(theta * 3);
        double newTheta = theta + Math.log(r);

        // Преобразование обратно в декартовы координаты
        double newX = newR * Math.cos(newTheta);
        double newY = newR * Math.sin(newTheta);

        return new Point(newX, newY);
    }
}
