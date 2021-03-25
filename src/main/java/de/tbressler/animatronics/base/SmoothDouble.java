package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.SmoothValue;

/**
 * A smooth value implementation for doubles.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class SmoothDouble extends SmoothValue<Double> {

    /**
     * A smooth value implementation for doubles.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public SmoothDouble(double valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
    }

    @Override
    protected Double calculateValueInBetween(Double lastValue, Double nextValue, double factor) {
        return calculateValue(lastValue, nextValue, factor);
    }

}
