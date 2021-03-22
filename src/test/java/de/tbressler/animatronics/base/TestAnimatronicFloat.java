package de.tbressler.animatronics.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for class AnimatronicFloat.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestAnimatronicFloat {

    // Class under test.
    private AnimatronicFloat animatronic;


    @Before
    public void setUp() throws Exception {
        animatronic = new AnimatronicFloat(1.0f);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(1.0f, animatronic.getValue(), 0.0f);
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(3.5f, animatronic.calculateValueInBetween(3f, 4f, 0.5D), 0.0f);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_returns3() {
        assertEquals(3f, animatronic.calculateValueInBetween(3f, 4f, 0D), 0.0f);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor1_returns4() {
        assertEquals(4f, animatronic.calculateValueInBetween(3f, 4f, 1D), 0.0f);
    }

}
