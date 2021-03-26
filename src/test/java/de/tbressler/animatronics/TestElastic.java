package de.tbressler.animatronics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Test for class ElasticValue.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestElastic {

    // Class under test.
    private Elastic elastic;

    // Mocks:
    private Elastic internalElastic = Mockito.mock(Elastic.class, "internalElasticValue");
    private Easing easing = Mockito.mock(Easing.class, "easing");
    private Object objectA = "object-A";
    private Object objectB = "object-B";
    private Object objectC = "object-C";
    private Object objectD = "object-D";


    @Before
    public void setUp() throws Exception {
        elastic = new Elastic(objectA, 1000, easing) {
            @Override
            protected Object calculateValueInBetween(Object lastValue, Object nextValue, double factor) {
                return internalElastic.calculateValueInBetween(lastValue, nextValue, factor);
            }
        };
    }


    // Constructor:

    @Test(expected = NullPointerException.class)
    public void constructor_withNullValue_throwsNPE() {
        elastic = new Elastic(null, 1000, easing) {
            @Override
            protected Object calculateValueInBetween(Object lastValue, Object nextValue, double factor) {
                return objectA;
            }
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withNegativeDuration_throwsIllegalArgumentException() {
        elastic = new Elastic(null, -1, easing) {
            @Override
            protected Object calculateValueInBetween(Object lastValue, Object nextValue, double factor) {
                return objectA;
            }
        };
    }

    @Test
    public void constructor_with2Parameters_doesntFail() {
        elastic = new Elastic(objectA, 1000) {
            @Override
            protected Object calculateValueInBetween(Object lastValue, Object nextValue, double factor) {
                return objectA;
            }
        };
    }


    // Set and get value:

    @Test
    public void getValue_whenValueWasNotChanged_returnsValueAtStart1() {
        assertEquals(objectA, elastic.getValue());
    }

    @Test
    public void getValue_whenValueWasNotChanged_returnsValueAtStart2() {
        long time = System.currentTimeMillis();
        assertEquals(objectA, elastic.getValue(time));
    }

    @Test
    public void setAndGetValue_simple() {
        elastic.setValue(objectB);

        when(easing.ease(anyDouble())).thenReturn(0.5d);
        when(internalElastic.calculateValueInBetween(eq(objectA), eq(objectB), eq(0.5d))).thenReturn(objectC);

        assertEquals(objectC, elastic.getValue(System.currentTimeMillis() + 100));
    }

    @Test
    public void setAndGetValue_whenNoDurationPassed_returnsLastValue() {
        elastic.setValue(100, objectB);

        assertEquals(objectA, elastic.getValue(100));
    }

    @Test
    public void setAndGetValue_whenDurationPassed_returnsNextValue1() {
        elastic.setValue(100, objectB);

        assertEquals(objectB, elastic.getValue(1100));
    }

    @Test
    public void setAndGetValue_whenDurationPassed_returnsNextValue2() {
        elastic.setValue(100, objectB);

        assertEquals(objectB, elastic.getValue(2000));
    }

    @Test
    public void setAndGetValue_whenValueWasChanged_returnsInterpolatedValue() {
        elastic.setValue(100, objectB);

        when(easing.ease(eq(500d/1000d))).thenReturn(0.5d);
        when(internalElastic.calculateValueInBetween(eq(objectA), eq(objectB), eq(500d/1000d))).thenReturn(objectC);

        assertEquals(objectC, elastic.getValue(600));

        verify(easing, times(1)).ease(eq(500d/1000d));
        verify(internalElastic, times(1)).calculateValueInBetween(eq(objectA), eq(objectB), anyDouble());
    }


    // Calculate value:

    @Test
    public void calculateValue_between3And4WithFactor0_1_returns3_1() {
        assertEquals(3.1D, elastic.calculateValue(3D, 4D, 0.1D), 0.0D);
    }

    @Test
    public void calculateValue_between3And4WithFactor0_returns3() {
        assertEquals(3D, elastic.calculateValue(3D, 4D, 0D), 0.0D);
    }

    @Test
    public void calculateValue_between3And4WithFactor1_returns4() {
        assertEquals(4D, elastic.calculateValue(3D, 4D, 1D), 0.0D);
    }

    @Test
    public void calculateValue_betweenZeroValuesAndFactor0_returns0() {
        assertEquals(0D, elastic.calculateValue(0D, 0D, 0D), 0.0D);
    }

    @Test
    public void calculateValue_betweenZeroValuesAndFactor1_returns0() {
        assertEquals(0D, elastic.calculateValue(0D, 0D, 1D), 0.0D);
    }

    @Test
    public void calculateValue_betweenNegativeValues() {
        assertEquals(-1.5D, elastic.calculateValue(-2D, -1D, 0.5D), 0.0D);
    }

}
