package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class SwirlTransformation extends AffineTransformation {

    public SwirlTransformation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        double affineX = point.x();
        double affineY = point.y();
        double r = Math.sqrt(affineX * affineX + affineY * affineY);
        double newX = affineX * Math.sin(Math.pow(r, 2)) - affineY * Math.cos(Math.pow(r, 2));
        double newY = affineX * Math.cos(Math.pow(r, 2)) + affineY * Math.sin(Math.pow(r, 2));
        return new Point(newX, newY);
    }
}
