package de.tbressler.animatronics.base;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * Test for class AnimatronicPoint2D.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestAnimatronicPoint2D {

    // Class under test.
    private AnimatronicPoint2D animatronic;


    @Before
    public void setUp() throws Exception {
        animatronic = new AnimatronicPoint2D(new Point2D.Double(2D, 3D));
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(new Point2D.Double(2D, 3D), animatronic.getValue());
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(new Point2D.Double(2.5D, 3.5D), animatronic.calculateValueInBetween(new Point2D.Double(2D, 3D), new Point2D.Double(3D, 4D), 0.5D));
    }

}
