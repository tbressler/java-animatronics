package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Animatronic;

/**
 * An animatronic for double values.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class AnimatronicDouble extends Animatronic<Double, AnimatronicDouble> {

    /**
     * An animatronic for double values.
     *
     * @param valueAtStart The value at start.
     */
    public AnimatronicDouble(double valueAtStart) {
        super(valueAtStart);
    }

    @Override
    protected Double calculateValueInBetween(Double lastValue, Double nextValue, double factor) {
        return calculateValue(lastValue, nextValue, factor);
    }

}
