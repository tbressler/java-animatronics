package de.tbressler.animatronics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Test for class Animatronic.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestAnimatronic {

    // Class under test.
    private Animatronic animatronic;

    // Mocks:
    private Animatronic internalAnimatronic = Mockito.mock(Animatronic.class, "internalAnimatronic");
    private Easing easing = Mockito.mock(Easing.class, "easing");
    private Object objectA = "object-A";
    private Object objectB = "object-B";
    private Object objectC = "object-C";
    private Object objectD = "object-D";


    @Before
    public void setUp() throws Exception {
        animatronic = new Animatronic(objectA) {
            @Override
            protected Object calculateValueInBetween(Object lastValue, Object nextValue, double factor) {
                return internalAnimatronic.calculateValueInBetween(lastValue, nextValue, factor);
            }
        };
    }


    // Constructor:

    @Test(expected = NullPointerException.class)
    public void constructor_withNullValue_throwsNPE() {
        animatronic = new Animatronic(null) {
            @Override
            protected Object calculateValueInBetween(Object lastValue, Object nextValue, double factor) {
                return objectA;
            }
        };
    }


    // Add keyframes:

    @Test(expected = NullPointerException.class)
    public void keyframe1_withNulLValue_throwsNPE() {
        animatronic.keyframe(null, 1000L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void keyframe1_withZeroDuration_throwsIllegalArgumentException() {
        animatronic.keyframe(objectB, 0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void keyframe1_withNegativeDuration_throwsIllegalArgumentException() {
        animatronic.keyframe(objectB, -1L);
    }


    @Test(expected = NullPointerException.class)
    public void keyframe2_withNulLValue_throwsNPE() {
        animatronic.keyframe(null, 1000L, easing);
    }

    @Test(expected = IllegalArgumentException.class)
    public void keyframe2_withZeroDuration_throwsIllegalArgumentException() {
        animatronic.keyframe(objectB, 0L, easing);
    }

    @Test(expected = IllegalArgumentException.class)
    public void keyframe2_withNegativeDuration_throwsIllegalArgumentException() {
        animatronic.keyframe(objectB, -1L, easing);
    }

    @Test
    public void keyframe2_withNulLEasing_doesntThrowNPE() {
        animatronic.keyframe(objectB, 1000L, null);
    }


    // Play:

    @Test
    public void play1_startsAnimationWithNoLoop() {
        animatronic.play();

        assertTrue(animatronic.hasStarted());
        assertFalse(animatronic.isLooped());
    }

    @Test
    public void play2_withLoopFalse_startsAnimationWithNoLoop() {
        animatronic.play(false);

        assertTrue(animatronic.hasStarted());
        assertFalse(animatronic.isLooped());
    }

    @Test
    public void play2_withLoopTrue_startsAnimationWithNoLoop() {
        animatronic.play(true);

        assertTrue(animatronic.hasStarted());
        assertTrue(animatronic.isLooped());
    }


    // Get value:

    @Test
    public void getValue_whenNoKeyframesAndNotStartedYet_returnsValueAtStart() {
        assertEquals(objectA, animatronic.getValue());
    }

    @Test
    public void getValue_whenNotStartedYet_returnsValueAtStart() {
        animatronic.keyframe(objectB, 10000);
        animatronic.keyframe(objectC, 5000);

        assertEquals(objectA, animatronic.getValue());
    }

    @Test
    public void getValue_whenStartedAndElapsedTime0_returnsValueAtStart() {

        long timeAtStart = 100L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, false);

        assertEquals(objectA, animatronic.getValue(timeAtStart));

        verify(internalAnimatronic, never()).calculateValueInBetween(any(), any(), anyDouble());
    }

    @Test
    public void getValue_whenStartedAndElapsedTime100_returnsValueBetweenAAndB() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 100L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, false);

        when(internalAnimatronic.calculateValueInBetween(eq(objectA), eq(objectB), eq(100D/1000D))).thenReturn(objectD);

        assertEquals(objectD, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, times(1)).calculateValueInBetween(eq(objectA), eq(objectB), anyDouble());
    }

    @Test
    public void getValue_whenStartedAndElapsedTime1000_returnsValueB() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 1000L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, false);

        assertEquals(objectB, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, never()).calculateValueInBetween(any(), any(), anyDouble());
    }

    @Test
    public void getValue_whenStartedAndElapsedTime1100_returnsValueBetweenBAndC() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 1100L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, false);

        when(internalAnimatronic.calculateValueInBetween(eq(objectB), eq(objectC), eq(100D/5000D))).thenReturn(objectD);

        assertEquals(objectD, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, times(1)).calculateValueInBetween(eq(objectB), eq(objectC), anyDouble());
    }

    @Test
    public void getValue_whenStartedAndElapsedTimeIsAtEndOfTimeline_returnLastValue() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 6000L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, false);

        assertEquals(objectC, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, never()).calculateValueInBetween(any(), any(), anyDouble());
    }

    @Test
    public void getValue_whenStartedAndElapsedTimeIsBehindTheTimeline_returnLastValue() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 6200L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, false);

        assertEquals(objectC, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, never()).calculateValueInBetween(any(), any(), anyDouble());
    }

    @Test
    public void getValue_whenStartedWithLoopAndElapsedTimeIsBehindTheTimeline_returnValueBetweenAAndB() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 6200L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, true);

        when(internalAnimatronic.calculateValueInBetween(eq(objectA), eq(objectB), eq(200D/1000D))).thenReturn(objectD);

        assertEquals(objectD, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, times(1)).calculateValueInBetween(eq(objectA), eq(objectB), anyDouble());
    }

    @Test
    public void getValue_whenStartedWithLoopAndElapsedTimeIsAtEndOfTimeline_returnValueAtStart() {

        long timeAtStart = 100L;
        long elapsedTimeInTimeline = 6000L;

        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 5000);
        animatronic.play(timeAtStart, true);

        assertEquals(objectA, animatronic.getValue(timeAtStart + elapsedTimeInTimeline));
        verify(internalAnimatronic, never()).calculateValueInBetween(any(), any(), anyDouble());
    }


    // Calculate value:

    @Test
    public void calculateValue_between3And4WithFactor0_1_returns3_1() {
        assertEquals(3.1D, animatronic.calculateValue(3D, 4D, 0.1D), 0.0D);
    }

    @Test
    public void calculateValue_between3And4WithFactor0_returns3() {
        assertEquals(3D, animatronic.calculateValue(3D, 4D, 0D), 0.0D);
    }

    @Test
    public void calculateValue_between3And4WithFactor1_returns4() {
        assertEquals(4D, animatronic.calculateValue(3D, 4D, 1D), 0.0D);
    }

    @Test
    public void calculateValue_betweenZeroValuesAndFactor0_returns0() {
        assertEquals(0D, animatronic.calculateValue(0D, 0D, 0D), 0.0D);
    }

    @Test
    public void calculateValue_betweenZeroValuesAndFactor1_returns0() {
        assertEquals(0D, animatronic.calculateValue(0D, 0D, 1D), 0.0D);
    }

    @Test
    public void calculateValue_betweenNegativeValues() {
        assertEquals(-1.5D, animatronic.calculateValue(-2D, -1D, 0.5D), 0.0D);
    }


    // Has started:

    @Test
    public void hasStarted_whenNotStartedYet_returnsFalse() {
        assertFalse(animatronic.hasStarted());
    }

    @Test
    public void hasStarted_whenStarted_returnsTrue() {
        animatronic.play(false);
        assertTrue(animatronic.hasStarted());
    }


    // Has finished:

    @Test
    public void hasFinished_whenNotStartedYet_returnsFalse() {
        assertTrue(animatronic.hasFinished());
    }

    @Test
    public void hasFinished_whenFinished_returnsTrue() {
        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 2000);
        animatronic.play(System.currentTimeMillis() - 3500L, false);
        assertTrue(animatronic.hasFinished());
    }

    @Test
    public void hasFinished_whenLooped_returnsFalse1() {
        animatronic.play(true);
        assertFalse(animatronic.hasFinished());
    }

    @Test
    public void hasFinished_whenLooped_returnsFalse2() {
        animatronic.keyframe(objectB, 1000);
        animatronic.keyframe(objectC, 2000);
        animatronic.play(System.currentTimeMillis() - 3500L, true);
        assertFalse(animatronic.hasFinished());
    }


    // Is looped:

    @Test
    public void isLooped_whenLooped_returnsTrue() {
        animatronic.play(true);
        assertTrue(animatronic.isLooped());
    }

    @Test
    public void isLooped_whenNotLooped_returnsFalse() {
        animatronic.play(false);
        assertFalse(animatronic.isLooped());
    }

}
