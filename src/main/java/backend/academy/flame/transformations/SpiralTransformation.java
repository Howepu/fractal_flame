package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class SpiralTransformation extends AffineTransformation {

    public SpiralTransformation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        double affineX = point.x();
        double affineY = point.y();

        double r = Math.sqrt(affineX * affineX + affineY * affineY);
        double s = Math.atan(affineX / affineY);
        double newX = 1 / r * (Math.cos(s) + Math.sin(r));
        double newY = 1 / r * (Math.sin(s) + Math.cos(r));

        return new Point(newX, newY);
    }
}
