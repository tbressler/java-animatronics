package de.tbressler.animatronics;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for the animation of any values.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public abstract class Animatronic<T, A extends Animatronic<T, A>> {

    /* The value at the start of the animation. */
    private final T valueAtStart;

    /* The timeline of the animation. */
    private final NavigableMap<Long, Keyframe<T>> timeline = new TreeMap<>();

    /* Position (in time) of the last keyframe. */
    private long lastKeyframeAt = 0L;

    /* The last value on the timeline. */
    private T lastValue;

    /* Time at the start of the animation. */
    private long startAt = -1L;

    /* True if the timeline should be looped. */
    private boolean loopTimeline = false;


    /**
     * An abstract class for the animation of any values.
     *
     * @param valueAtStart The value at the start of the animation.
     */
    public Animatronic(T valueAtStart) {
        this.valueAtStart = requireNonNull(valueAtStart, "The valueAtStart must not be null");
        timeline.put(lastKeyframeAt, new Keyframe<>(valueAtStart, 0L));
    }


    /**
     * Adds a keyframe to the animation. The animation between the last
     * and this keyframe will be animated without an easing (just linear
     * interpolation).
     *
     * It is recommended to use #keyframe(value, duration, easing) instead for smooth easing
     * between the values.
     *
     * @param value The value of this keyframe.
     * @param duration The duration between the last and this keyframe (in ms).
     * @return The animatronic itself for simple chaining.
     */
    public final A keyframe(T value, long duration) {
        return keyframe(value, duration, null);
    }

    /**
     * Adds a keyframe to the animation.
     *
     * @param value The value of this keyframe.
     * @param duration The duration between the last and this keyframe (in ms).
     * @param easing The easing function.
     * @return The animatronic itself for simple chaining.
     */
    public final A keyframe(T value, long duration, Easing easing) {
        requireNonNull(value, "The value must not be null");
        if (duration <= 0L) throw new IllegalArgumentException("The duration must be greater than 0!");

        lastKeyframeAt = lastKeyframeAt + duration;
        lastValue = value;
        timeline.put(lastKeyframeAt, new Keyframe<>(value, duration, (easing == null) ? Easings.noEasing() : easing));

        return (A) this;
    }


    /**
     * Start the animation.
     *
     * @return The animatronic itself for simple chaining.
     */
    public final A play() {
        return play(false);
    }

    /**
     * Start the animation.
     *
     * @param loop True if the animation should be looped.
     * @return The animatronic itself for simple chaining.
     */
    public final A play(boolean loop) {
        return play(System.currentTimeMillis(), loop);
    }

    /**
     * Start the animation.
     *
     * @param time The system time.
     * @param loop True if the animation should be looped.
     * @return The animatronic itself for simple chaining.
     */
    public final A play(long time, boolean loop) {
        startAt = time;
        loopTimeline = loop;
        return (A) this;
    }


    /**
     * Get the (interpolated) value at the current point in time. If the
     * animation hasn't started yet, the 'value at start' is returned. If
     * the animation has finished, the last value of the timeline is
     * returned.
     *
     * @return The current value.
     */
    public final T getValue() {
        return getValue(System.currentTimeMillis());
    }

    /**
     * Get the (interpolated) value at the current point in time. If the
     * animation hasn't started yet, the 'value at start' is returned. If
     * the animation has finished, the last value of the timeline is
     * returned.
     *
     * @param time The system time.
     * @return The current value.
     */
    public final T getValue(long time) {

        if (!hasStarted(time))
            return valueAtStart;

        // Get current timeline position.
        long timing = getTiming(time);

        // If time at start, then return first value.
        if (timing == 0)
            return valueAtStart;

        // If timeline position after last keyframe, then
        // return last value.
        if (timing > lastKeyframeAt)
            return lastValue;

        Map.Entry<Long, Keyframe<T>> lastEntry = timeline.floorEntry(timing);
        Map.Entry<Long, Keyframe<T>> nextEntry = timeline.ceilingEntry(timing);

        // If timeline position is exactly one keyframe, then
        // return the value at that position.
        if (lastEntry.equals(nextEntry))
            return lastEntry.getValue().getValue();

        double timeInTimeslot = timing - lastEntry.getKey();

        Easing easing = nextEntry.getValue().getEasing();
        return calculateValueInBetween(lastEntry.getValue().getValue(), nextEntry.getValue().getValue(),
                easing.ease(timeInTimeslot / (double) nextEntry.getValue().getDuration()));
    }

    /* Returns the current position in the timeline. */
    private long getTiming(long time) {
        return loopTimeline ? (time - startAt) % lastKeyframeAt : (time - startAt);
    }


    /**
     * Returns the interpolated value between the last and the next value based
     * on the factor. Normally the formula should look like this:
     *
     * interpolated = last + ((next - last) * factor)
     *
     * @param lastValue The last value.
     * @param nextValue The next value.
     * @param factor The factor.
     * @return The interpolated value.
     */
    protected abstract T calculateValueInBetween(T lastValue, T nextValue, double factor);

    /**
     * Helper function for the calculation of the interpolated value.
     *
     * @param lastValue The last value.
     * @param nextValue The next value.
     * @param factor The factor.
     * @return The interpolated value.
     */
    protected double calculateValue(double lastValue, double nextValue, double factor) {
        return lastValue + ((nextValue - lastValue) * factor);
    }


    /**
     * Returns true if the animation has started.
     *
     * @return True if started.
     */
    public final boolean hasStarted() {
        return hasStarted(System.currentTimeMillis());
    }

    /**
     * Returns true if the animation has started.
     *
     * @param time The system time.
     * @return True if started.
     */
    public final boolean hasStarted(long time) {
        return (startAt > -1L) && (time >= startAt);
    }


    /**
     * Returns true if the animation has finished. If the
     * animation is a loop, the method always returns false.
     * If the animation hasn't started yet, then the method
     * also returns true.
     *
     * @return True if finished.
     */
    public final boolean hasFinished() {
        return hasFinished(System.currentTimeMillis());
    }

    /**
     * Returns true if the animation has finished. If the
     * animation is a loop, the method always returns false.
     *
     * @param time The system time.
     * @return True if finished.
     */
    public final boolean hasFinished(long time) {
        if (loopTimeline)
            return false;
        return getTiming(time) >= lastKeyframeAt;
    }


    /**
     * Returns true if the timeline is looped.
     *
     * @return True if lopped.
     */
    public final boolean isLooped() {
        return loopTimeline;
    }

}
