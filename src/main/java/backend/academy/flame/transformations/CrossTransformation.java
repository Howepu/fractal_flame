package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class CrossTransformation extends AffineTransformation {

    public CrossTransformation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        double affineX = point.x();
        double affineY = point.y();

        double denominator = Math.pow(affineX * affineX - affineY * affineY, 2);
        double n = Math.sqrt(1 / denominator);

        double newX = n * affineX;
        double newY = n * affineY;

        return new Point(newX, newY);
    }
}
