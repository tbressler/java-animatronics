package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Easing;
import de.tbressler.animatronics.Elastic;

import java.awt.*;

/**
 * An elastic value implementation for colors.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class ElasticColor extends Elastic<Color> {

    /**
     * An elastic value implementation for colors.
     *
     * @param valueAtStart The color at start.
     * @param duration The duration of the smoothing.
     */
    public ElasticColor(Color valueAtStart, long duration) {
        super(valueAtStart, duration);
    }

    /**
     * An elastic value implementation for colors.
     *
     * @param valueAtStart The color at start.
     * @param duration The duration of the smoothing.
     * @param easing The easing function.
     */
    public ElasticColor(Color valueAtStart, long duration, Easing easing) {
        super(valueAtStart, duration, easing);
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
