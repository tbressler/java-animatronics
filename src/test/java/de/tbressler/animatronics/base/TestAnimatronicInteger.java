package de.tbressler.animatronics.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for class AnimatronicInteger.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestAnimatronicInteger {

    // Class under test.
    private AnimatronicInteger animatronic;


    @BeforeEach
    public void setUp() throws Exception {
        animatronic = new AnimatronicInteger(100);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(100, animatronic.getValue(), 0);
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(350, animatronic.calculateValueInBetween(300, 400, 0.5D), 0);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_returns3() {
        assertEquals(300, animatronic.calculateValueInBetween(300, 400, 0D), 0);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor1_returns4() {
        assertEquals(400, animatronic.calculateValueInBetween(300, 400, 1D), 0.0D);
    }

}
