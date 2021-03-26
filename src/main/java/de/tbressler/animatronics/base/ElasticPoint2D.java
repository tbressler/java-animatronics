package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.Elastic;

import java.awt.geom.Point2D;

/**
 * An elastic value for Point2D.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class ElasticPoint2D extends Elastic<Point2D> {

    /**
     * An elastic value for Point2D.
     *
     * @param valueAtStart The point at start.
     * @param duration The duration of the smoothing.
     */
    public ElasticPoint2D(Point2D valueAtStart, long duration) {
        super(valueAtStart, duration);
    }

    /**
     * An elastic value for Point2D.
     *
     * @param valueAtStart The point at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public ElasticPoint2D(Point2D valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
    }

    @Override
    protected Point2D calculateValueInBetween(Point2D lastValue, Point2D nextValue, double factor) {
        return new Point2D.Double(
                calculateValue(lastValue.getX(), nextValue.getX(), factor),
                calculateValue(lastValue.getY(), nextValue.getY(), factor)
        );
    }

}
