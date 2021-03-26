package de.tbressler.animatronics;

import static java.util.Objects.requireNonNull;

/**
 * @author Tobias Breßler
 * @version 1.0
 */
public abstract class ElasticValue<T> {

    private final Easing easing;
    private final long duration;

    private T lastValue;
    private T nextValue = null;

    private long changedAt = -1L;
    private long changedSince = -1L;


    public ElasticValue(T valueAtStart, long duration, Easing easing) {
        this.easing = easing;
        this.duration = duration;
        this.lastValue = valueAtStart;
    }


    public void setValue(T value) {
        setValue(System.currentTimeMillis(), value);
    }

    public void setValue(long time, T value) {
        lastValue = getValue(time);
        nextValue = requireNonNull(value, "The value must not be null");
        changedAt = time;
        changedSince = time + duration;
    }


    public T getValue() {
        return getValue(System.currentTimeMillis());
    }

    public T getValue(long time) {
        if (changedAt == -1L)
            return lastValue;
        if (time >= changedSince)
            return nextValue;

        double factor = easing.ease(((double) time - (double) changedAt) / (double) duration);
        return calculateValueInBetween(lastValue, nextValue, factor);
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
