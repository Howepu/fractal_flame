package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import backend.academy.flame.image.Colorable;
import java.util.Random;
import lombok.Getter;

@SuppressWarnings("checkstyle:MagicNumber")
@Getter
public class CircularTransformation implements Transformation, Colorable {
    private static final Random RANDOM = new Random();

    private double a;
    private double b;
    private double d;
    private double e;
    private double c;
    private double f;
    private int red;
    private int green;
    private int blue;

    public CircularTransformation() {
        generateCoefficients();
        this.red = RANDOM.nextInt(NUM);
        this.green = RANDOM.nextInt(NUM);
        this.blue = RANDOM.nextInt(NUM);
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
        return apply(point.x(), point.y());
    }

    @Override
    public Point apply(double x, double y) {
        // Линейное преобразование
        double transformedX = a * x + b * y + c;
        double transformedY = d * x + e * y + f;

        // Преобразование в полярные координаты
        double r = Math.sqrt(transformedX * transformedX + transformedY * transformedY);
        double theta = Math.atan2(transformedY, transformedX);

        // Уникальная логика CircularTransformation
        double newR = r * Math.sin(theta * 3);
        double newTheta = theta + Math.log1p(r); // log1p для большей численной стабильности

        // Преобразование обратно в декартовы координаты
        double newX = newR * Math.cos(newTheta);
        double newY = newR * Math.sin(newTheta);

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
