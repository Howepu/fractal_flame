package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import backend.academy.flame.image.Colorable;
import java.util.Random;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class LinearTransformation implements Transformation, Colorable {
    private double a;
    private double b;
    private double d;
    private double e;
    private double c;
    private double f;
    private int red;
    private int green;
    private int blue;
    private final int maxcolor = 256;
    private final int num = -2;
    private static final Random RANDOM = new Random();

    public LinearTransformation() {
        generateCoefficients();
        this.red = RANDOM.nextInt(maxcolor);
        this.green = RANDOM.nextInt(maxcolor);
        this.blue = RANDOM.nextInt(maxcolor);

    }

    private void generateCoefficients() {
        do {
            this.a = randomInRange(-1, 1);
            this.b = randomInRange(-1, 1);
            this.d = randomInRange(-1, 1);
            this.e = randomInRange(-1, 1);
            this.c = randomInRange(num, 2); // Транслируемое значение (допустим, шире диапазон)
            this.f = randomInRange(num, 2); // Транслируемое значение
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
        return new Point(x, y);
    }

    @Override public Point apply(double x, double y) {
        double newX = a * x + b * y + c;
        double newY = d * x + e * y + f;
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
