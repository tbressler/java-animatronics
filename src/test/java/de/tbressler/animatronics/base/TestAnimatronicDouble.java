package de.tbressler.animatronics.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for class AnimatronicDouble.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestAnimatronicDouble {

    // Class under test.
    private AnimatronicDouble animatronic;


    @Before
    public void setUp() throws Exception {
        animatronic = new AnimatronicDouble(1.0D);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(1.0D, animatronic.getValue(), 0.0D);
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(3.5D, animatronic.calculateValueInBetween(3D, 4D, 0.5D), 0.0D);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_returns3() {
        assertEquals(3D, animatronic.calculateValueInBetween(3D, 4D, 0D), 0.0D);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor1_returns4() {
        assertEquals(4D, animatronic.calculateValueInBetween(3D, 4D, 1D), 0.0D);
    }

}
