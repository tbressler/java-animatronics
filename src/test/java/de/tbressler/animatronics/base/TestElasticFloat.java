package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for class ElasticFloat.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestElasticFloat {

    // Class under test.
    private ElasticFloat elastic;


    @BeforeEach
    public void setUp() throws Exception {
        elastic = new ElasticFloat(1.0f, 1000, Easings.noEasing());
    }


    // Constructor:

    @Test
    public void constructor_doesntThrowException() {
        new ElasticFloat(10f, 1000);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(1.0f, elastic.getValue(), 0.0f);
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(3.5f, elastic.calculateValueInBetween(3f, 4f, 0.5D), 0.0f);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_returns3() {
        assertEquals(3f, elastic.calculateValueInBetween(3f, 4f, 0D), 0.0f);
    }

    @Test
    public void calculateValueInBetween_with3And4AndFactor1_returns4() {
        assertEquals(4f, elastic.calculateValueInBetween(3f, 4f, 1D), 0.0f);
    }

}
