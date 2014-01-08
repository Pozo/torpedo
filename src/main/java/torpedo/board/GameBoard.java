package torpedo.board;

import java.util.Collection;
import java.util.Set;

import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;

/**
 * GameBoard.
 * @author Zoltan_Polgar
 *
 */
public interface GameBoard {
    /**
     * getAllShip.
     * @return ships
     */
    Collection<Ship> getAllShip();
    /**
     * getAllShipCoordinates.
     * @return ship coordinates
     */
    Set<Coordinate> getAllShipCoordinates();
    /**
     * getPlacedShipNumber.
     * @return placed ship number
     */
    int getPlacedShipNumber();
    /**
     * getFireCount.
     * @return fire count
     */
    int getFireCount();
    /**
     * incrementFireCount.
     */
    void incrementFireCount();
    /**
     * isAllShipWrecked.
     * @return is all ship wrecked
     */
    boolean isAllShipWrecked();
    /**
     * placeShip.
     * @param ship ship
     * @return place was successful
     */
    boolean placeShip(Ship ship);
    /**
     * isCoordinateOnTheBoard.
     * @param coordinate coordinate
     * @return coordinate is on board
     */
    boolean isCoordinateOnTheBoard(Coordinate coordinate);
    /**
     * addShip.
     * @param ship ship
     * @return ship succesful added
     */
    boolean addShip(Ship ship);
    /**
     * getWidth.
     * @return width
     */
    int getWidth();
    /**
     * getHeight.
     * @return height
     */
    int getHeight();
}
