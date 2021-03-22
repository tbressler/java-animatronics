package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Animatronic;

/**
 * An animatronic for integer values.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class AnimatronicInteger extends Animatronic<Integer, AnimatronicInteger> {

    /**
     * An animatronic for integer values.
     *
     * @param valueAtStart The value at start.
     */
    public AnimatronicInteger(int valueAtStart) {
        super(valueAtStart);
    }

    @Override
    protected Integer calculateValueInBetween(Integer lastValue, Integer nextValue, double factor) {
        return (int) ((double) lastValue + ((double) (nextValue - lastValue) * factor));
    }

}
