package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for class AnimatronicPoint2D.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class TestElasticPoint2D {

    // Class under test.
    private ElasticPoint2D elastic;


    @BeforeEach
    public void setUp() throws Exception {
        elastic = new ElasticPoint2D(new Point2D.Double(2D, 3D), 1000, Easings.noEasing());
    }


    // Constructor:

    @Test
    public void constructor_doesntThrowException() {
        new ElasticPoint2D(new Point2D.Double(2D, 3D), 1000);
    }


    // Get value at start:

    @Test
    public void getValue_returnsValueFromConstructor() {
        assertEquals(new Point2D.Double(2D, 3D), elastic.getValue());
    }


    // Calculate value in between:

    @Test
    public void calculateValueInBetween_with3And4AndFactor0_5_returns3_5() {
        assertEquals(new Point2D.Double(2.5D, 3.5D), elastic.calculateValueInBetween(new Point2D.Double(2D, 3D), new Point2D.Double(3D, 4D), 0.5D));
    }

}
