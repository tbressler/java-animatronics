package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for class ElasticDouble.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestElasticDouble {

    // Class under test.
    private ElasticDouble elastic;


    @BeforeEach
    public void setUp() throws Exception {
        elastic = new ElasticDouble(1.0D, 1000, Easings.noEasing());
    }


    // Constructor:

    @Test
    public void constructor_doesntThrowException() {
        new ElasticDouble(10d, 1000);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(1.0D, elastic.getValue(), 0.0D);
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(3.5D, elastic.calculateValueInBetween(3D, 4D, 0.5D), 0.0D);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_returns3() {
        assertEquals(3D, elastic.calculateValueInBetween(3D, 4D, 0D), 0.0D);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor1_returns4() {
        assertEquals(4D, elastic.calculateValueInBetween(3D, 4D, 1D), 0.0D);
    }

}
