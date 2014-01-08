package torpedo.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.aim.RandomTarget;
import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;
import torpedo.ship.providers.RandomShipGenerator;
import torpedo.ship.providers.ShipFromFile;
/**
 * GameBoard implementation.
 * @author Zoltan_Polgar
 *
 */
public class SquareGameBoard implements GameBoard {
    private static final int BOARD_MINIMUM_SIZE = 5;
    private static final int BOARD_MAXIMUM_SIZE = 25;

    private static final Logger LOGGER = LoggerFactory.getLogger(SquareGameBoard.class);

    private final int boardSize;
    private final ShipPlacer shipPlacer;
    private List<Ship> shipsOnBoard = new ArrayList<Ship>();

    private int firecount;

    /**
     * SquareGameBoard.
     * @param boardSize board size
     */
    public SquareGameBoard(int boardSize) {
        if (boardSize < BOARD_MINIMUM_SIZE || boardSize > BOARD_MAXIMUM_SIZE) {
            throw new IllegalArgumentException("The board size must be between 5 andd 25");
        }
        this.boardSize = boardSize;
        shipPlacer = new ShipPlacer(this);
    }
    /**
     * getAllShipCoordinates.
     * @return coordinates
     */
    public Set<Coordinate> getAllShipCoordinates() {
        Set<Coordinate> coordinates = new HashSet<Coordinate>();

        for (Ship ship : shipsOnBoard) {
            coordinates.addAll(ship.getCoordinates());
        }

        return coordinates;
    }

    public Collection<Ship> getAllShip() {
        return shipsOnBoard;
    }

    public int getPlacedShipNumber() {
        return shipsOnBoard.size();
    }
    /**
     * isAllShipWrecked.
     * @return is all ship wrecked
     */
    public boolean isAllShipWrecked() {
        boolean allWrecked = true;

        for (Ship ship : shipsOnBoard) {
            allWrecked &= ship.isWrecked();
        }
        return allWrecked;
    }
    public boolean placeShip(Ship ship) {
        return shipPlacer.placeShip(ship);
    }

    public int getWidth() {
        return boardSize;
    }

    public int getHeight() {
        return boardSize;
    }

    public int getFireCount() {
        return firecount;
    }

    public boolean isAllCoordinateHitted() {
        return getFireCount() < getHeight() * getWidth();
    }

    public void incrementFireCount() {
        firecount++;
    }

    public boolean isCoordinateOnTheBoard(Coordinate coordinate) {
        if (coordinate.getX() < getWidth() && coordinate.getY() < getHeight()) {
            return true;
        }
        throw new IllegalArgumentException("The Coordinate must be on the Board !");
    }

    public boolean addShip(Ship ship) {
        return shipsOnBoard.add(ship);
    }

    public void initRandom(int shipCount) {
        while (getPlacedShipNumber() != shipCount) {
            try {
                Coordinate coordinate = new RandomTarget(getWidth()).getCoordinate();
                Ship ship = new RandomShipGenerator(RandomShipGenerator.RECTANGLE_SIZE).getShip();
                ship.transformCoordinates(coordinate);

                placeShip(ship);
            } catch (IllegalArgumentException exception) {
                LOGGER.error(exception.getMessage());
            }
        }
    }

    public void initFromFile(String string) {
        ShipFromFile shipsFromFile = new ShipFromFile(string);
        List<Ship> ships = shipsFromFile.getShips();

        for (Ship ship : ships) {
            boolean success = false;
            while (!success) {
                try {
                    Coordinate coordinate = new RandomTarget(getWidth()).getCoordinate();
                    ship.transformCoordinates(coordinate);

                    success = placeShip(ship);
                } catch (IllegalArgumentException exception) {
                    LOGGER.error(exception.getMessage());
                }
            }
        }
    }
}
