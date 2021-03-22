package de.tbressler.animatronics;

/**
 * Easing implementations.
 * See https://easings.net/ for more information.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class Easings {

    // Constants which are used by the easing functions:
    private final static double C1 = 1.70158D;
    private final static double C2 = C1 * 1.525D;
    private final static double C3 = C1 + 1D;
    private final static double C4 = (2D * Math.PI) / 3D;
    private final static double C5 = (2D * Math.PI) / 4.5D;


    /** No easing. */
    public static Easing noEasing() {
        return x -> x;
    }

    /** EaseInSine: https://easings.net/#easeInSine */
    public static Easing easeInSine() {
        return x -> 1D - Math.cos((x * Math.PI) / 2D);
    }

    /** EaseOutSine: https://easings.net/#easeOutSine */
    public static Easing easeOutSine() {
        return x -> Math.sin((x * Math.PI) / 2D);
    }

    /** EaseInOutSine: https://easings.net/#easeInOutSine */
    public static Easing easeInOutSine() {
        return x -> -(Math.cos(Math.PI * x) - 1D) / 2D;
    }

    /** EaseInCubic: https://easings.net/#easeInCubic */
    public static Easing easeInCubic() {
        return x -> x * x * x;
    }

    /** EaseOutCubic: https://easings.net/#easeOutCubic */
    public static Easing easeOutCubic() {
        return x -> 1 - Math.pow(1D - x, 3D);
    }

    /** EaseInOutCubic: https://easings.net/#easeInOutCubic */
    public static Easing easeInOutCubic() {
        return x -> x < 0.5D ? 4D * x * x * x : 1D - Math.pow(-2D * x + 2D, 3D) / 2D;
    }

    /** EaseOutElastic: https://easings.net/#easeOutElastic */
    public static Easing easeOutElastic() {
        return x -> x == 0D
                ? 0D
                : x == 1D
                ? 1D
                : Math.pow(2D, -10D * x) * Math.sin((x * 10D - 0.75D) * C4) + 1D;
    }

    /** EaseInElastic: https://easings.net/#easeInElastic */
    public static Easing easeInElastic() {
        return x -> x == 0D
                ? 0D
                : x == 1D
                ? 1D
                : -Math.pow(2D, 10D * x - 10D) * Math.sin((x * 10D - 10.75D) * C4);
    }

    /** EaseInOutElastic: https://easings.net/#easeInOutElastic */
    public static Easing easeInOutElastic() {
        return x -> x == 0D
                ? 0D
                : x == 1D
                ? 1D
                : x < 0.5D
                ? -(Math.pow(2D, 20D * x - 10D) * Math.sin((20D * x - 11.125D) * C5)) / 2D
                : (Math.pow(2D, -20D * x + 10D) * Math.sin((20D * x - 11.125D) * C5)) / 2D + 1D;
    }

    /** EaseInExpo: https://easings.net/#easeInExpo */
    public static Easing easeInExpo() {
        return x -> x == 0D ? 0D : Math.pow(2D, 10D * x - 10D);
    }

    /** EaseOutExpo: https://easings.net/#easeOutExpo */
    public static Easing easeOutExpo() {
        return x -> x == 1D ? 1D : 1D - Math.pow(2D, -10D * x);
    }

    /** EaseInOutExpo: https://easings.net/#easeInOutExpo */
    public static Easing easeInOutExpo() {
        return x -> x == 0D
                ? 0D
                : x == 1D
                ? 1D
                : x < 0.5D ? Math.pow(2D, 20D * x - 10D) / 2D
                : (2D - Math.pow(2D, -20D * x + 10D)) / 2D;
    }

    /** EaseInBack: https://easings.net/#easeInBack */
    public static Easing easeInBack() {
        return x -> C3 * x * x * x - C1 * x * x;
    }

    /** EaseOutBack: https://easings.net/#easeOutBack */
    public static Easing easeOutBack() {
        return x -> 1D + C3 * Math.pow(x - 1D, 3D) + C1 * Math.pow(x - 1D, 2D);
    }

    /** EaseInOutBack: https://easings.net/#easeInOutBack */
    public static Easing easeInOutBack() {
        return x -> x < 0.5D
                ? (Math.pow(2D * x, 2D) * ((C2 + 1D) * 2D * x - C2)) / 2D
                : (Math.pow(2D * x - 2D, 2D) * ((C2 + 1D) * (x * 2D - 2D) + C2) + 2D) / 2D;
    }

}
