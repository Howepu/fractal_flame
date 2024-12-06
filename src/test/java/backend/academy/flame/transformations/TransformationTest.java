package backend.academy.flame.transformations;



import backend.academy.flame.entities.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransformationTest {

    private Point inputPoint;
    private CrossTransformation crossTransformation;
    private EyeFishTransformation eyefishTransformation;
    private HeartTransformation heartTransformation;
    private SpiralTransformation spiralTransformation;
    private SwirlTransformation swirlTransformation;
    private TangentTransformation tangentTransformation;
    private WavesTransformation wavesTransformation;
    private CircularTransformation circularTransformation;


    @BeforeEach
    void setUp() {
        // Инициализация объектов трансформаций и точки
        inputPoint = new Point(2, 3);
        crossTransformation = new CrossTransformation();
        eyefishTransformation = new EyeFishTransformation();
        heartTransformation = new HeartTransformation();
        spiralTransformation = new SpiralTransformation();
        swirlTransformation = new SwirlTransformation();
        tangentTransformation = new TangentTransformation();
        wavesTransformation = new WavesTransformation();
        circularTransformation = new CircularTransformation();
    }

    @Test
    void testCrossTransformation() {
        Point transformedPoint = crossTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertEquals(0.4, transformedPoint.x(), 0.001);
        assertEquals(0.6, transformedPoint.y(), 0.001);
    }

    @Test
    void testEyefishTransformation() {
        Point transformedPoint = eyefishTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertEquals(0.868, transformedPoint.x(), 0.001);  // Проверка с ожидаемым значением для X
        assertEquals(1.302, transformedPoint.y(), 0.001);  // Используем точное значение для Y
    }


    @Test
    void testHeartTransformation() {
        Point transformedPoint = heartTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testSpiralTransformation() {
        Point transformedPoint = spiralTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testSwirlTransformation() {
        Point transformedPoint = swirlTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testTangentTransformation() {
        Point transformedPoint = tangentTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testWavesTransformation() {
        Point transformedPoint = wavesTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertEquals(2, transformedPoint.x(), 0.001);  // Проверка с ожидаемым значением
        assertEquals(3, transformedPoint.y(), 0.001);  // Проверка с ожидаемым значением
    }

    @Test
    void testCircleTransformation() {
        Point transformedPoint = circularTransformation.apply(inputPoint);
        if (transformedPoint == null) {
            fail("Transformed point is null");
        }
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }
}
