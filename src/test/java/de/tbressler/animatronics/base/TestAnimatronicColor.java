package de.tbressler.animatronics.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test for class AnimatronicColor.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestAnimatronicColor {

    // Class under test.
    private AnimatronicColor animatronic;


    @BeforeEach
    public void setUp() throws Exception {
        animatronic = new AnimatronicColor(new Color(100, 100, 100, 100));
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(new Color(100, 100, 100, 100), animatronic.getValue());
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween1() {
        Color colorA = new Color(100, 100, 100, 100);
        Color colorB = new Color(200, 0, 200, 100);
        Color result = new Color(150, 50, 150, 100);
        assertEquals(result, animatronic.calculateValueInBetween(colorA, colorB, 0.5D));
    }

    @Test
    public void calculateValueInBetween2() {
        Color colorA = new Color(100, 100, 100, 100);
        Color colorB = new Color(255, 255, 255, 255);
        Color result = new Color(255, 255, 255, 255);
        assertEquals(result, animatronic.calculateValueInBetween(colorA, colorB, 1.5D));
    }

    @Test
    public void calculateValueInBetween3() {
        Color colorA = new Color(0, 0, 0, 0);
        Color colorB = new Color(255, 255, 255, 255);
        Color result = new Color(0, 0, 0, 0);
        assertEquals(result, animatronic.calculateValueInBetween(colorA, colorB, -0.5D));
    }

}
