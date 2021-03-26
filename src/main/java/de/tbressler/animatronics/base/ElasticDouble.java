package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.Elastic;

/**
 * An elastic value implementation for doubles.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class ElasticDouble extends Elastic<Double> {

    /**
     * An elastic value implementation for doubles.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     */
    public ElasticDouble(double valueAtStart, long duration) {
        super(valueAtStart, duration);
    }

    /**
     * An elastic value implementation for doubles.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public ElasticDouble(double valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
    }

    @Override
    protected Double calculateValueInBetween(Double lastValue, Double nextValue, double factor) {
        return calculateValue(lastValue, nextValue, factor);
    }

}
