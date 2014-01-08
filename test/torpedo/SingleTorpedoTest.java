package torpedo;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.aim.ExactTarget;
import torpedo.board.ConsolePrinter;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;
import torpedo.weapon.SingleTorpedo;

/**
 * SingleTorpedoTest.
 * @author Zoltan_Polgar
 *
 */
public class SingleTorpedoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleTorpedoTest.class);

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private List<Coordinate> getCoordinates() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(10, 10));
        coordinates.add(new Coordinate(10, 9));

        return coordinates;
    }

    private List<Coordinate> getCoordinatesTwo() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(8, 8));
        coordinates.add(new Coordinate(7, 8));

        return coordinates;
    }

    @Test
    public void testSingleTorpedo() {
        //ExactTarget target = new ExactTarget(10, 10);
        SquareGameBoard player1Board = new SquareGameBoard(15);

        Coordinate coordinate = new Coordinate(0, 0);

        Ship ship = new Ship(getCoordinates());
        ship.transformCoordinates(coordinate);
        player1Board.placeShip(ship);

        Ship ship2 = new Ship(getCoordinatesTwo());
        ship2.transformCoordinates(coordinate);
        player1Board.placeShip(ship2);

        new ConsolePrinter(player1Board).print(System.out);
        SingleTorpedo playerTorpedo = new SingleTorpedo(player1Board);

        playerTorpedo.fire(new ExactTarget(1, 1).getCoordinate());

        playerTorpedo.fire(new ExactTarget(10, 10).getCoordinate());
        playerTorpedo.fire(new ExactTarget(10, 10).getCoordinate());
        playerTorpedo.fire(new ExactTarget(10, 9).getCoordinate());

        playerTorpedo.fire(new ExactTarget(0, 0).getCoordinate());

        playerTorpedo.fire(new ExactTarget(8, 8).getCoordinate());
        playerTorpedo.fire(new ExactTarget(7, 8).getCoordinate());

        LOGGER.info(player1Board.getAllShip().toString());
    }

    @Test
    public void testFireTargetingSystem() {
        fail("Not yet implemented");
    }

    @Test
    public void testFireIntInt() {
        fail("Not yet implemented");
    }

}
