package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.SmoothValue;

/**
 * A smooth value implementation for integers.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public class SmoothInteger extends SmoothValue<Integer> {

    /**
     * A smooth value implementation for integers.
     *
     * @param valueAtStart The value at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public SmoothInteger(int valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
    }

    @Override
    protected Integer calculateValueInBetween(Integer lastValue, Integer nextValue, double factor) {
        return (int) ((double) lastValue + ((double) (nextValue - lastValue) * factor));
    }

}
