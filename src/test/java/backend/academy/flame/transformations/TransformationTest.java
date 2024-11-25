package backend.academy.flame.transformations;



import backend.academy.flame.entities.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransformationTest {

    private Point inputPoint;
    private CrossTransformation crossTransformation;
    private EyefishTransformation eyefishTransformation;
    private HeartTransformation heartTransformation;
    private HyperbolicTransformation hyperbolicTransformation;
    private SpiralTransformation spiralTransformation;
    private SwirlTransformation swirlTransformation;
    private TangentTransfortmation tangentTransformation;
    private WavesTransformation wavesTransformation;

    @BeforeEach
    void setUp() {
        // Инициализация объектов трансформаций и точки
        inputPoint = new Point(2, 3);
        crossTransformation = new CrossTransformation(1, 0, 0, 0, 1, 0);
        eyefishTransformation = new EyefishTransformation(1, 0, 0, 0, 1, 0);
        heartTransformation = new HeartTransformation(1, 0, 0, 0, 1, 0);
        hyperbolicTransformation = new HyperbolicTransformation(1, 0, 0, 0, 1, 0);
        spiralTransformation = new SpiralTransformation(1, 0, 0, 0, 1, 0);
        swirlTransformation = new SwirlTransformation(1, 0, 0, 0, 1, 0);
        tangentTransformation = new TangentTransfortmation(1, 0, 0, 0, 1, 0);
        wavesTransformation = new WavesTransformation(1, 0, 0, 0, 1, 0);
    }

    @Test
    void testCrossTransformation() {
        Point transformedPoint = crossTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertEquals(0.4, transformedPoint.x(), 0.001);
        assertEquals(0.6000000000000001, transformedPoint.y(), 0.001);
    }

    @Test
    void testEyefishTransformation() {
        Point transformedPoint = eyefishTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertEquals(0.8685170918213297, transformedPoint.x(), 0.001);  // Проверка с ожидаемым значением для X
        assertEquals(1.3027756377319946, transformedPoint.y(), 0.001);  // Используем точное значение для Y
    }


    @Test
    void testHeartTransformation() {
        Point transformedPoint = heartTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testHyperbolicTransformation() {
        Point transformedPoint = hyperbolicTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testSpiralTransformation() {
        Point transformedPoint = spiralTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testSwirlTransformation() {
        Point transformedPoint = swirlTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testTangentTransformation() {
        Point transformedPoint = tangentTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertTrue(transformedPoint.x() != 0);
        assertTrue(transformedPoint.y() != 0);
    }

    @Test
    void testWavesTransformation() {
        Point transformedPoint = wavesTransformation.apply(inputPoint);
        assertNotNull(transformedPoint);
        assertEquals(2.9974949866040546, transformedPoint.x(), 0.001);  // Проверка с ожидаемым значением
        assertEquals(3.395249731376525, transformedPoint.y(), 0.001);  // Проверка с ожидаемым значением
    }
}
