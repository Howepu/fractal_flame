package backend.academy.flame.transformations;

import backend.academy.flame.image.Colorable;
import java.util.Random;
import lombok.Getter;

@SuppressWarnings("checkstyle:MagicNumber")
@Getter
public abstract class AbstractTransformation implements Transformation, Colorable {
    private static final Random RANDOM = new Random();
    private static final int NUM = 256;

    private double a;
    private double b;
    private double d;
    private double e;
    private double c;
    private double f;
    private int red;
    private int green;
    private int blue;

    public AbstractTransformation() {
        generateCoefficients();
        this.red = RANDOM.nextInt(NUM);
        this.green = RANDOM.nextInt(NUM);
        this.blue = RANDOM.nextInt(NUM);
    }

    protected void generateCoefficients() {
        do {
            this.a = randomInRange(-1, 1);
            this.b = randomInRange(-1, 1);
            this.d = randomInRange(-1, 1);
            this.e = randomInRange(-1, 1);
            this.c = randomInRange(-2, 2);
            this.f = randomInRange(-2, 2);
        } while (!isValid(a, b, d, e));
    }

    protected boolean isValid(double a, double b, double d, double e) {
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
