package torpedo.board;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;

/**
 * ShipPlacerTest.
 * @author Zoltan_Polgar
 *
 */
public class ShipPlacerTest {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ShipPlacerTest.class);

    private List<Coordinate> getCoordinates() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(10, 10));
        coordinates.add(new Coordinate(10, 11));
        coordinates.add(new Coordinate(9, 11));
        coordinates.add(new Coordinate(9, 12));

        return coordinates;
    }

    private List<Coordinate> getSlimShipCoordinates() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(1, 0));
        coordinates.add(new Coordinate(0, 0));

        return coordinates;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testShipPlacer() {
        fail("Not yet implemented");
    }

    @Test
    public void testPlaceShip() {
        SquareGameBoard gameBoard = new SquareGameBoard(5);
        gameBoard.placeShip(new Ship(getSlimShipCoordinates()));

        //LOGGER.info(gameBoard.getPlacedShipNumber());
        //RandomTarget randomTarget = new RandomTarget(1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceShipCollosion() {
        SquareGameBoard gameBoard = new SquareGameBoard(5);
        gameBoard.placeShip(new Ship(getSlimShipCoordinates()));
        gameBoard.placeShip(new Ship(getSlimShipCoordinates()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceShipOutOfBoard() {
        SquareGameBoard gameBoard = new SquareGameBoard(5);
        gameBoard.placeShip(new Ship(getSlimShipCoordinates()));
        gameBoard.placeShip(new Ship(getCoordinates()));

    }
}
