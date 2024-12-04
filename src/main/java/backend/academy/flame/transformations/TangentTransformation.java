package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import backend.academy.flame.image.Colorable;
import lombok.Getter;
import java.util.Random;


@Getter
public class TangentTransformation implements Transformation, Colorable {
    private double a, b, d, e, c, f;
    private int red, green, blue;
    private static final Random RANDOM = new Random();

    public TangentTransformation() {
        generateCoefficients();
        this.red = RANDOM.nextInt(256);
        this.green = RANDOM.nextInt(256);
        this.blue = RANDOM.nextInt(256);

    }

    private void generateCoefficients() {
        do {
            this.a = randomInRange(-1, 1);
            this.b = randomInRange(-1, 1);
            this.d = randomInRange(-1, 1);
            this.e = randomInRange(-1, 1);
            this.c = randomInRange(-2, 2);
            this.f = randomInRange(-2, 2);
        } while (!isValid(a, b, d, e));
    }

    private boolean isValid(double a, double b, double d, double e) {
        double condition1 = a * a + d * d;
        double condition2 = b * b + e * e;
        double determinant = a * e - b * d;
        boolean condition3 = condition1 + condition2 < 1 + determinant * determinant;
        return condition1 < 1 && condition2 < 1 && condition3;
    }

    private double randomInRange(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }


    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x,y);
    }

    @Override public Point apply(double x, double y) {
        double newX = a * x + b * y+ c;
        double newY = d * x + e * y + f;
        newX = Math.sin(newX) / Math.cos(newY);
        newY = Math.tan(newY);
        return new Point(newX, newY);
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }
}

