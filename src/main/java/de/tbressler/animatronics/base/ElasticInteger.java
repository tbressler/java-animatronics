package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.Elastic;

/**
 * An elastic value implementation for integers.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class ElasticInteger extends Elastic<Integer> {

    /**
     * An elastic value implementation for integers.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     */
    public ElasticInteger(int valueAtStart, long duration) {
        super(valueAtStart, duration);
    }

    /**
     * An elastic value implementation for integers.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public ElasticInteger(int valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
    }

    @Override
    protected Integer calculateValueInBetween(Integer lastValue, Integer nextValue, double factor) {
        return (int) ((double) lastValue + ((double) (nextValue - lastValue) * factor));
    }

}
