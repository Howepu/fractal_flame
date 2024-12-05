package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;

@SuppressWarnings("checkstyle:AvoidNoArgumentSuperConstructorCall")
public class EyefishTransformation extends AbstractTransformation {

    public EyefishTransformation() {
        super();
    }

    @Override
    public Point apply(Point point) {
        double x = a() * point.x() + b() * point.y() + c();
        double y = d() * point.x() + e() * point.y() + f();
        return new Point(x, y);
    }

    @Override
    public Point apply(double x, double y) {
        double newX = a() * x + b() * y + c();
        double newY = d() * x + e() * y + f();
        double r = Math.sqrt(newX * newX + newY * newY);

        // Apply eyefish distortion
        newX = (2 / (r + 1)) * newX;
        newY = (2 / (r + 1)) * newY;

        return new Point(newX, newY);
    }
}
