package de.tbressler.animatronics;

/**
 * Interface for different easing functions.
 *
 * Easing functions specify the rate of change of a parameter over time. Objects in real life
 * don’t just start and stop instantly, and almost never move at a constant speed.
 *
 * Implementations of common easing function can be found in class Easings.
 *
 * See also https://easings.net/.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public interface Easing {

    /**
     * Ease the given value. The variable x represents the absolute progress of the
     * animation in the bounds of 0 (beginning of the animation) and 1 (end of animation).
     *
     * The output can be greater than 1 or lower than 0 regarding to the easing function.
     *
     * @param x Progress of the animation (between 0 and 1).
     * @return Eased output.
     */
    double ease(double x);

}
