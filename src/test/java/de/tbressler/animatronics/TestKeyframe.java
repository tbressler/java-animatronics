package de.tbressler.animatronics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Tests for class Keyframe.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestKeyframe {

    // Class under test.
    private Keyframe keyframe;

    // Mocks:
    private Easing easing = Mockito.mock(Easing.class, "easing");
    private Object objectA = "object-A";
    private Object objectB = "object-B";
    private Object objectC = "object-C";
    private Object objectD = "object-D";


    @Before
    public void setUp() throws Exception {
        keyframe = new Keyframe(objectA, 2310L, easing);
    }


    // Constructor:

    @Test(expected = NullPointerException.class)
    public void constructor_withNullValue_throwsNPE() {
        new Keyframe<>(null, 2000L, easing);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withNegativeDuration_throwsIllegalArgumentException() {
        new Keyframe<>(objectA, -1L, easing);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_withNullEasing_throwsNPE() {
        new Keyframe<>(objectA, 1000L, null);
    }


    // Get value:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(objectA, keyframe.getValue());
    }


    // Get duration:

    @Test
    public void getDuration_returnsValueFromConstructor() {
        assertEquals(2310L, keyframe.getDuration());
    }


    // Get duration:

    @Test
    public void getEasing_returnsValueFromConstructor() {
        assertEquals(easing, keyframe.getEasing());
    }

}
