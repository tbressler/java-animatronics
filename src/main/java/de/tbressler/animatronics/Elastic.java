package de.tbressler.animatronics;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for elastic values.
 *
 * @author Tobias Breßler
 * @version 1.0
 */
public abstract class Elastic<T> {

    /* The easing from the last to this keyframe. */
    private final Easing easing;

    /* The duration between the last and the next value. */
    private final long duration;

    /* The last value. */
    private T lastValue;

    /* The next value. */
    private T nextValue = null;

    /* The point in time the value has changed. */
    private long changedAt = -1L;

    /* The point in time the value should be changed +
       to the next value. */
    private long changedSince = -1L;


    /**
     * An abstract class for elastic values.
     *
     * @param valueAtStart The value at start, must not be null.
     * @param duration The duration between values.
     */
    public Elastic(T valueAtStart, long duration) {
        this(valueAtStart, duration, null);
    }

    /**
     * An abstract class for elastic values.
     *
     * @param valueAtStart The value at start, must not be null.
     * @param duration The duration between values.
     * @param easing The easing function.
     */
    public Elastic(T valueAtStart, long duration, Easing easing) {
        if (duration < 0) throw new IllegalArgumentException("The duration must be greater or equal than 0!");
        this.easing = (easing != null) ? easing : Easings.noEasing();
        this.duration = duration;
        this.lastValue = requireNonNull(valueAtStart, "The value must not be null");
    }


    /**
     * Set the new (next) value.
     *
     * @param value The new value, must not be null.
     */
    public void setValue(T value) {
        setValue(System.currentTimeMillis(), value);
    }

    /**
     * Set the new (next) value.
     *
     * @param time The point in time.
     * @param value The new value, must not be null.
     */
    public void setValue(long time, T value) {
        lastValue = getValue(time);
        nextValue = requireNonNull(value, "The value must not be null");
        changedAt = time;
        changedSince = time + duration;
    }


    /**
     * Get the (interpolated) value at the current point in time.
     *
     * @return The current value.
     */
    public T getValue() {
        return getValue(System.currentTimeMillis());
    }

    /**
     * Get the (interpolated) value at the current point in time.
     *
     * @param time The point in time.
     * @return The current value.
     */
    public T getValue(long time) {
        if ((changedAt == -1L) || (changedAt == time))
            return lastValue;
        else if (time >= changedSince)
            return nextValue;
        return calculateValueInBetween(lastValue, nextValue,
                easing.ease(((double) time - (double) changedAt) / (double) duration));
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

}
