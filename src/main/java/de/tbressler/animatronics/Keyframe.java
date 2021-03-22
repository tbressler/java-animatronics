package de.tbressler.animatronics;

import static java.util.Objects.requireNonNull;

/**
 * A keyframe in the timeline of the animation.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class Keyframe<T> {

    /* The value at the keyframe. */
    private final T value;

    /* The duration between the last and this keyframe. */
    private final long duration;

    /* The easing from the last to this keyframe. */
    private final Easing easing;


    /**
     * A keyframe in the timeline of the animation.
     *
     * @param value The value at the keyframe.
     * @param duration The duration between the last and this keyframe (in ms).
     */
    public Keyframe(T value, long duration) {
        this(value, duration, Easings.noEasing());
    }

    /**
     * A keyframe in the timeline of the animation.
     *
     * @param value The value at the keyframe.
     * @param duration The duration between the last and this keyframe (in ms).
     * @param easing The easing function.
     */
    public Keyframe(T value, long duration, Easing easing) {
        requireNonNull(value, "The value must not be null!");
        requireNonNull(easing, "The easing must not be null!");
        if (duration < 0L) throw new IllegalArgumentException("The duration must be greater or equal than 0!");

        this.value = value;
        this.duration = duration;
        this.easing = easing;
    }

    /**
     * Returns the value at this keyframe.
     *
     * @return The value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns the duration between the last and this keyframe.
     *
     * @return The duration (in ms).
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Returns the easing function.
     *
     * @return The easing function.
     */
    public Easing getEasing() {
        return easing;
    }

}
