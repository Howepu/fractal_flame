package backend.academy.flame.transformations;

import backend.academy.flame.entities.Point;
import lombok.AllArgsConstructor;

@SuppressWarnings("checkstyle:MagicNumber")
@AllArgsConstructor
public class VortexFlowerTransformation implements Transformation {
    private final double rotationStrength; // Сила вращения
    private final double radialScale;      // Масштаб радиальных деформаций
    private final double waveAmplitude;    // Амплитуда синусоидальных искажений
    private final double waveFrequency;    // Частота синусоидальных искажений

    @Override
    public Point apply(Point point) {
        // Вычисление полярных координат
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double s = Math.atan2(point.y(), point.x());

        // Вихревое вращение
        double twistedAngle = s + rotationStrength * Math.sin(r * waveFrequency);

        // Радиальное растяжение с эффектом цветка
        double radialEffect = 1 + radialScale * Math.sin(waveFrequency * s);

        // Новые координаты
        double newX = radialEffect * r * Math.cos(twistedAngle) + waveAmplitude  * Math.sin(s * waveFrequency);
        double newY = radialEffect * r * Math.sin(twistedAngle) + waveAmplitude * Math.cos(s * waveFrequency);

        return new Point(newX, newY);
    }

    // Метод для создания стандартного цветочного вихря
    public static VortexFlowerTransformation createDefault() {
        return new VortexFlowerTransformation(0.5, 0.8, 0.2, 3.0);
    }

    public static VortexFlowerTransformation createFlowerWithSmallPetals() {
        return new VortexFlowerTransformation(1, 0.5, 0.1, 6);
    }

    public static VortexFlowerTransformation createFlowerWithWidePetals() {
        return new VortexFlowerTransformation(0.2, 1, 0.3, 2);
    }


}
