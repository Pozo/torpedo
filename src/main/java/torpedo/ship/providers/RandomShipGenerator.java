package torpedo.ship.providers;

import java.util.ArrayList;
import java.util.List;

import torpedo.aim.RandomTarget;
import torpedo.coordinate.Coordinate;
import torpedo.coordinate.CoordinateArrayUtils;
import torpedo.coordinate.CoordinateArrayUtils.Action;
import torpedo.coordinate.CoordinateUtils;
import torpedo.ship.Ship;

/**
 * RandomShipGenerator.
 * @author Zoltan_Polgar
 *
 */
public class RandomShipGenerator {
    public static final int RECTANGLE_SIZE = 4;
    private final int rectangleSize;

    /**
     * RandomShipGenerator.
     * @param rectangleSize rectangle size
     */
    public RandomShipGenerator(int rectangleSize) {
        this.rectangleSize = rectangleSize;
    }
    /**
     * getShip.
     * @return ship
     */
    public Ship getShip() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();

        int tryCount = rectangleSize * rectangleSize;

        for (int i = 0; i < tryCount; i++) {
            Coordinate coordinate = new RandomTarget(rectangleSize).getCoordinate();

            if (coordinates.isEmpty()) {
                coordinates.add(coordinate);
            } else {
                if (validateCoordinateIntegrability(coordinates, coordinate)) {
                    coordinates.add(coordinate);
                }
            }
        }

        return new Ship(coordinates);
    }

    private static boolean validateCoordinateIntegrability(List<Coordinate> coordinates, Coordinate coordinate) {
        return CoordinateUtils.isNeighborStrict(coordinates.get(coordinates.size() - 1), coordinate) && !coordinates.contains(coordinate)
                && isValidCoordinateDistance(coordinates, coordinate, RECTANGLE_SIZE);
    }

    private static boolean isValidCoordinateDistance(List<Coordinate> coordinates, Coordinate coordinate, int rectangleSize) {
        CoordinateArrayUtils arrayUtils = new CoordinateArrayUtils(coordinates);

        if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_LEFT) - coordinate.getX()) > rectangleSize) {
            return false;
        }
        if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_RIGT) - coordinate.getX()) > rectangleSize) {
            return false;
        }
        if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_UPPER) - coordinate.getY()) > rectangleSize) {
            return false;
        }
        if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_LOWER) - coordinate.getY()) > rectangleSize) {
            return false;
        }
        return true;
    }
}
