package de.tbressler.animatronics;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


    @BeforeEach
    public void setUp() throws Exception {
        keyframe = new Keyframe(objectA, 2310L, easing);
    }


    // Constructor:

    @Test
    public void constructor_withNullValue_throwsNPE() {
        assertThrows(NullPointerException.class, () -> new Keyframe<>(null, 2000L, easing));
    }

    @Test
    public void constructor_withNegativeDuration_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Keyframe<>(objectA, -1L, easing));
    }

    @Test
    public void constructor_withNullEasing_throwsNPE() {
        assertThrows(NullPointerException.class, () -> new Keyframe<>(objectA, 1000L, null));
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
