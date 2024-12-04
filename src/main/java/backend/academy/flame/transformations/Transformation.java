package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    int NUM = 256;

    Point apply(Point point);

    Point apply(double newX, double newY);
}
