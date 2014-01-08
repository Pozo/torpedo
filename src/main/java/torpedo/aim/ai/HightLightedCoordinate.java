package torpedo.aim.ai;

import torpedo.coordinate.Coordinate;
/**
 * HightLightedCoordinate.
 * @author Zoltan_Polgar
 *
 */
public class HightLightedCoordinate {
    private static final int TOP = 0x1;
    private static final int BOTTOM = 0x2;
    private static final int LEFT = 0x4;
    private static final int RIGHT = 0x8;

    private static final int NO_ONE = 0x0;

    private final Coordinate invalidCoordinate = new Coordinate(-1, -1);

    private int tryed = NO_ONE;

    private Coordinate coordinate = invalidCoordinate;
    private int boardSize;

    /**
     * highLight.
     * @param coordinate coordinate
     * @param boardSize board size
     */
    public void highLight(Coordinate coordinate, int boardSize) {
        this.coordinate = coordinate;
        this.boardSize = boardSize;
    }
    /**
     * getCoordinateByEnviromentAnalisys.
     * @return next coordinate
     */
    public Coordinate getCoordinateByEnviromentAnalisys() {
        if (coordinate.hasPossibleTopNeighbor(boardSize) && (tryed & TOP) == NO_ONE) {
            coordinate.offset(new Coordinate(0, 1));
            addFlag(TOP);

        } else if (coordinate.hasPossibleBottomNeighbor() && (tryed & BOTTOM) == NO_ONE) {
            coordinate.offset(new Coordinate(0, -1));
            addFlag(BOTTOM);

        } else if (coordinate.hasPossibleRightNeighbor(boardSize) && (tryed & RIGHT) == NO_ONE) {
            coordinate.offset(new Coordinate(1, 0));
            addFlag(RIGHT);

        } else if (coordinate.hasPossibleLeftNeighbor() && (tryed & LEFT) == NO_ONE) {
            coordinate.offset(new Coordinate(-1, 0));
            addFlag(LEFT);
        }
        return coordinate;
    }

    private void addFlag(int flag) {
        tryed |= flag;

    }
    /**
     * hasAvailableTargetYet.
     * @return has available target
     */
    public boolean hasAvailableTargetYet() {
        return isReachedTop() || isReachedBottom() || isReachedLeft() || isReachedRight();
    }
    private boolean isReachedTop() {
        return coordinate.hasPossibleTopNeighbor(boardSize) && (tryed & TOP) == NO_ONE;
    }
    private boolean isReachedBottom() {
        return coordinate.hasPossibleBottomNeighbor() && (tryed & BOTTOM) == NO_ONE;
    }
    private boolean isReachedLeft() {
        return coordinate.hasPossibleLeftNeighbor() && (tryed & LEFT) == NO_ONE;
    }
    private boolean isReachedRight() {
        return coordinate.hasPossibleRightNeighbor(boardSize) && (tryed & RIGHT) == NO_ONE;
    }
    /**
     * hasHightLightedCoordinate.
     * @return has highlighted coordinate
     */
    public boolean hasHightLightedCoordinate() {
        return !coordinate.equals(invalidCoordinate);
    }
    /**
     * reset.
     */
    public void reset() {
        coordinate = invalidCoordinate;
        tryed = NO_ONE;
    }
}
