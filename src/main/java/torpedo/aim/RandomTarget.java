package torpedo.aim;

import java.util.Random;

import torpedo.coordinate.Coordinate;
/**
 * RandomTarget.
 * @author Zoltan_Polgar
 *
 */
public class RandomTarget implements TargetingSystem {
    private static Random rand = new Random();
    private final int bound;

    /**
     * RandomTarget.
     * @param bound bound size
     */
    public RandomTarget(int bound) {
        this.bound = bound;
    }
    public int getBound() {
        return bound;
    }
    public Coordinate getCoordinate() {
        return generateCoordinate();
    }
    /**
     * generateCoordinate.
     * @return generated coordinate
     */
    protected Coordinate generateCoordinate() {
        Coordinate randomCoordinate = new Coordinate(rand.nextInt(bound), rand.nextInt(bound));
        return randomCoordinate;
    }
}
