package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.Elastic;

/**
 * An elastic value implementation for floats.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class ElasticFloat extends Elastic<Float> {

    /**
     * An elastic value implementation for floats.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     */
    public ElasticFloat(float valueAtStart, long duration) {
        super(valueAtStart, duration);
    }

    /**
     * A smooth value implementation for floats.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public ElasticFloat(float valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
    }

    @Override
    protected Float calculateValueInBetween(Float lastValue, Float nextValue, double factor) {
        return lastValue + ((nextValue - lastValue) * (float) factor);
    }

}
