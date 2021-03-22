package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Animatronic;

import java.awt.*;

/**
 * An animatronic for colors.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class AnimatronicColor extends Animatronic<Color, AnimatronicColor> {

    /**
     * An animatronic for colors.
     *
     * @param valueAtStart The color at start.
     */
    public AnimatronicColor(Color valueAtStart) {
        super(valueAtStart);
    }

    @Override
    protected Color calculateValueInBetween(Color lastValue, Color nextValue, double factor) {
        return new Color(
                (int) calculateValue(lastValue.getRed(), nextValue.getRed(), factor),
                (int) calculateValue(lastValue.getGreen(), nextValue.getGreen(), factor),
                (int) calculateValue(lastValue.getBlue(), nextValue.getBlue(), factor),
                (int) calculateValue(lastValue.getAlpha(), nextValue.getAlpha(), factor)
        );
    }

    @Override
    protected double calculateValue(double last, double next, double factor) {
        // Limit values between 0 and 255.
        return Math.max(0, Math.min(255, super.calculateValue(last, next, factor)));
    }

}
