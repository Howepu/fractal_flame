package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

public class TangentTransfortmation extends AffineTransformation {

    public TangentTransfortmation(double a, double b, double c, double d, double e, double f) {
        super(a, b, c, d, e, f);
    }

    @Override
    protected Point transformPoint(Point point) {
        double affineX = point.x();
        double affineY = point.y();
        double newX = Math.sin(affineX) / Math.cos(affineY);
        double newY = Math.tan(affineY);
        return new Point(newX, newY);
    }
}
