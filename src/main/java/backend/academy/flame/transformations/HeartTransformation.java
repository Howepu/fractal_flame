package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class HeartTransformation extends AffineTransformation {

    public HeartTransformation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        double affineX = point.x();
        double affineY = point.y();

        double r = Math.sqrt(affineX * affineX + affineY * affineY);
        double theta = Math.atan(affineX / affineY);
        double newX = r * Math.sin(theta * r);
        double newY = -r * Math.cos(theta * r);
        return new Point(newX, newY);
    }
}
