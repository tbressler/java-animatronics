package de.tbressler.animatronics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for class Easings.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestEasings {

    private Easing easing;


    @Test
    public void noEasing() {
        easing = Easings.noEasing();

        assertNotNull(easing);
        assertEquals(0.5, easing.ease(0.5), 0.0);
    }

    @Test
    public void testEaseInSine() {
        easing = Easings.easeInSine();

        assertNotNull(easing);
        assertEquals(0.3, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseOutSine() {
        easing = Easings.easeOutSine();

        assertNotNull(easing);
        assertEquals(0.7, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInOutSine() {
        easing = Easings.easeInOutSine();

        assertNotNull(easing);
        assertEquals(0.5, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInCubic() {
        easing = Easings.easeInCubic();

        assertNotNull(easing);
        assertEquals(0.12, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseOutCubic() {
        easing = Easings.easeOutCubic();

        assertNotNull(easing);
        assertEquals(0.88, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInOutCubic() {
        easing = Easings.easeInOutCubic();

        assertNotNull(easing);
        assertEquals(0.5, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseOutElastic() {
        easing = Easings.easeOutElastic();

        assertNotNull(easing);
        assertEquals(1.01, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInElastic() {
        easing = Easings.easeInElastic();

        assertNotNull(easing);
        assertEquals(-0.01, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInOutElastic() {
        easing = Easings.easeInOutElastic();

        assertNotNull(easing);
        assertEquals(0.5, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInExpo() {
        easing = Easings.easeInExpo();

        assertNotNull(easing);
        assertEquals(0.03, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseOutExpo() {
        easing = Easings.easeOutExpo();

        assertNotNull(easing);
        assertEquals(0.96, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInOutExpo() {
        easing = Easings.easeInOutExpo();

        assertNotNull(easing);
        assertEquals(0.5, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInBack() {
        easing = Easings.easeInBack();

        assertNotNull(easing);
        assertEquals(-0.08, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseOutBack() {
        easing = Easings.easeOutBack();

        assertNotNull(easing);
        assertEquals(1.08, easing.ease(0.5), 0.01);
    }

    @Test
    public void testEaseInOutBack() {
        easing = Easings.easeInOutBack();

        assertNotNull(easing);
        assertEquals(0.5, easing.ease(0.5), 0.01);
    }

}
