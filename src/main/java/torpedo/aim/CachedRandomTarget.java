package torpedo.aim;

import java.util.HashSet;
import java.util.Set;

import torpedo.coordinate.Coordinate;

/**
 * CachedRandomTarget.
 *
 * @author Zoltan_Polgar
 *
 */
public class CachedRandomTarget implements TargetingSystem {
    private static final Set<Coordinate> PREVIUS_COORDINATES = new HashSet<Coordinate>();
    private final RandomTarget randomTarget;

    /**
     * CachedRandomTarget.
     *
     * @param bound bound size
     */
    public CachedRandomTarget(int bound) {
        randomTarget = new RandomTarget(bound);
    }
    /**
     * getCoordinate.
     * @return coordinate
     */
    public Coordinate getCoordinate() {
        Coordinate randomCoordinate = randomTarget.generateCoordinate();

        while (!PREVIUS_COORDINATES.add(randomCoordinate)) {

            randomCoordinate = randomTarget.generateCoordinate();
        }
        return randomCoordinate;
    }
    /**
     * AddToCache.
     * @param coordinate coordinate
     * @return successful added
     */
    public boolean addToCache(Coordinate coordinate) {
        return PREVIUS_COORDINATES.add(coordinate);
    }

    public Set<Coordinate> getCachedCoordinates() {
        return PREVIUS_COORDINATES;
    }
}
