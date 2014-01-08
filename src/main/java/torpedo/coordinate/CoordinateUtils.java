package torpedo.coordinate;

/**
 * CoordinateUtils.
 * @author Zoltan_Polgar
 *
 */
public final class CoordinateUtils {
    private CoordinateUtils() { }
    /**
     * isNeighborWeak.
     * @param coordinate coordinate 1
     * @param coordinate1 coordinate 2
     * @return is neighbor weak
     */
    public static boolean isNeighborWeak(Coordinate coordinate, Coordinate coordinate1) {
        return coordinate1.equals(coordinate) || (isXNeighborWeak(coordinate, coordinate1) && isYNeighborWeak(coordinate, coordinate1));
    }
    /**
     * isNeighborStrict.
     * @param coordinate coordinate 1
     * @param coordinate1 coordinate 2
     * @return is neighbor strict
     */
    public static boolean isNeighborStrict(Coordinate coordinate, Coordinate coordinate1) {
        return coordinate1.equals(coordinate) || isXNeighborStrict(coordinate, coordinate1) || isYNeighborStrict(coordinate, coordinate1);
    }

    private static boolean isYNeighborStrict(Coordinate coordinate, Coordinate coordinate1) {
        return (coordinate.getY() - 1 == coordinate1.getY() || coordinate.getY() + 1 == coordinate1.getY())
                && coordinate1.getX() == coordinate.getX();
    }

    private static boolean isXNeighborStrict(Coordinate coordinate, Coordinate coordinate1) {
        return (coordinate.getX() - 1 == coordinate1.getX() || coordinate.getX() + 1 == coordinate1.getX())
                && coordinate1.getY() == coordinate.getY();
    }

    private static boolean isYNeighborWeak(Coordinate coordinate, Coordinate coordinate1) {
        return coordinate.getY() - 1 == coordinate1.getY() || coordinate.getY() + 1 == coordinate1.getY();
    }

    private static boolean isXNeighborWeak(Coordinate coordinate, Coordinate coordinate1) {
        return coordinate.getX() - 1 == coordinate1.getX() || coordinate.getX() + 1 == coordinate1.getX();
    }
}
