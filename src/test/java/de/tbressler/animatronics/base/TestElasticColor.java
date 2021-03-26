package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easings;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Test for class AnimatronicColor.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestElasticColor {

    // Class under test.
    private ElasticColor elastic;


    @Before
    public void setUp() throws Exception {
        elastic = new ElasticColor(new Color(100, 100, 100, 100), 1000, Easings.noEasing());
    }


    // Constructor:

    @Test
    public void constructor_doesntThrowException() {
        new ElasticColor(new Color(100, 100, 100, 100), 1000);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(new Color(100, 100, 100, 100), elastic.getValue());
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween1() {
        Color colorA = new Color(100, 100, 100, 100);
        Color colorB = new Color(200, 0, 200, 100);
        Color result = new Color(150, 50, 150, 100);
        assertEquals(result, elastic.calculateValueInBetween(colorA, colorB, 0.5D));
    }

    @Test
    public void calculateValueInBetween2() {
        Color colorA = new Color(100, 100, 100, 100);
        Color colorB = new Color(255, 255, 255, 255);
        Color result = new Color(255, 255, 255, 255);
        assertEquals(result, elastic.calculateValueInBetween(colorA, colorB, 1.5D));
    }

    @Test
    public void calculateValueInBetween3() {
        Color colorA = new Color(0, 0, 0, 0);
        Color colorB = new Color(255, 255, 255, 255);
        Color result = new Color(0, 0, 0, 0);
        assertEquals(result, elastic.calculateValueInBetween(colorA, colorB, -0.5D));
    }

}
