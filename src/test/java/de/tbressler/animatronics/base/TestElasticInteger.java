package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easings;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for class ElasticInteger.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestElasticInteger {

    // Class under test.
    private ElasticInteger elastic;


    @Before
    public void setUp() throws Exception {
        elastic = new ElasticInteger(100, 1000, Easings.noEasing());
    }


    // Constructor:

    @Test
    public void constructor_doesntThrowException() {
        new ElasticInteger(10, 1000);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(100, elastic.getValue(), 0);
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(350, elastic.calculateValueInBetween(300, 400, 0.5D), 0);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_returns3() {
        assertEquals(300, elastic.calculateValueInBetween(300, 400, 0D), 0);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor1_returns4() {
        assertEquals(400, elastic.calculateValueInBetween(300, 400, 1D), 0.0D);
    }

}
