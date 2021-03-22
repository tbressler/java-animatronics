package de.tbressler.animatronics.base;

import de.tbressler.animatronics.Animatronic;

import java.awt.geom.Point2D;

/**
 * An animatronic for Point2D.
 *
 * @author Tobias Bressler
 * @version 1.0
 */
public class AnimatronicPoint2D extends Animatronic<Point2D, AnimatronicPoint2D> {

    /**
     * An animatronic for Point2D.
     *
     * @param valueAtStart The point at start.
     */
    public AnimatronicPoint2D(Point2D valueAtStart) {
        super(valueAtStart);
    }

    @Override
    protected Point2D calculateValueInBetween(Point2D lastValue, Point2D nextValue, double factor) {
        return new Point2D.Double(
                calculateValue(lastValue.getX(), nextValue.getX(), factor),
                calculateValue(lastValue.getY(), nextValue.getY(), factor)
        );
    }

}
