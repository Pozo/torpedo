package torpedo.aim;

import torpedo.coordinate.Coordinate;
/**
 * ExactTarget.
 * @author Zoltan_Polgar
 *
 */
public class ExactTarget implements TargetingSystem {
    private final int x;
    private final int y;

    /**
     * ExactTarget.
     * @param x x coordinate
     * @param y y coordinate
     */
    public ExactTarget(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

}
