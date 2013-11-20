package torpedo;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import torpedo.aim.ExactTarget;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.utils.GameBoardPrinter;

public class SingleTorpedoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	private ArrayList<Coordinate> getCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(10, 10));
		coordinates.add(new Coordinate(10, 9));
		
		return coordinates;
	}
	private ArrayList<Coordinate> getCoordinatesTwo() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(8, 8));
		coordinates.add(new Coordinate(7, 8));
		
		return coordinates;
	}
	@Test
	public void testSingleTorpedo() {
		ExactTarget target = new ExactTarget(10, 10);
		SquareGameBoard player1Board = new SquareGameBoard(15);
		
		Coordinate coordinate = new Coordinate(0, 0);
		
		Ship ship = new Ship(getCoordinates());
		ship.transformCoordinates(coordinate);
		player1Board.placeShip(ship);
		
		Ship ship2 = new Ship(getCoordinatesTwo());
		ship2.transformCoordinates(coordinate);
		player1Board.placeShip(ship2);
		
		
		new GameBoardPrinter(player1Board).print();
		SingleTorpedo playerTorpedo = new SingleTorpedo(player1Board);
		
		playerTorpedo.fire(new ExactTarget(1, 1));
		
		playerTorpedo.fire(new ExactTarget(10, 10));
		playerTorpedo.fire(new ExactTarget(10, 10));
		playerTorpedo.fire(new ExactTarget(10, 9));
		
		playerTorpedo.fire(new ExactTarget(0, 0));
		
		playerTorpedo.fire(new ExactTarget(8, 8));
		playerTorpedo.fire(new ExactTarget(7, 8));
		
		System.out.println(player1Board.getAllShip());
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
