package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Animatronic;

/**
 * An animatronic for float values.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class AnimatronicFloat extends Animatronic<Float, AnimatronicFloat> {

    /**
     * An animatronic for float values.
     *
     * @param valueAtStart The value at start.
     */
    public AnimatronicFloat(float valueAtStart) {
        super(valueAtStart);
    }

    @Override
    protected Float calculateValueInBetween(Float lastValue, Float nextValue, double factor) {
        return lastValue + ((nextValue - lastValue) * (float) factor);
    }

}
