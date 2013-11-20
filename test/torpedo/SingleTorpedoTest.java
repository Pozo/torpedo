package torpedo;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.ConcreteTarget;
import torpedo.coordinate.Coordinate;

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
		ConcreteTarget target = new ConcreteTarget(10, 10);
		SquareGameBoard player1Board = new SquareGameBoard(15);
		
		player1Board.placeShipTo(new Coordinate(0, 0), new Ship(getCoordinates()));
		player1Board.placeShipTo(new Coordinate(0, 0), new Ship(getCoordinatesTwo()));
		
		SingleTorpedo playerTorpedo = new SingleTorpedo(player1Board);
		
		playerTorpedo.fire(new ConcreteTarget(1, 1));
		
		playerTorpedo.fire(new ConcreteTarget(10, 10));
		playerTorpedo.fire(new ConcreteTarget(10, 10));
		playerTorpedo.fire(new ConcreteTarget(10, 9));
		
		playerTorpedo.fire(new ConcreteTarget(0, 0));
		
		playerTorpedo.fire(new ConcreteTarget(8, 8));
		playerTorpedo.fire(new ConcreteTarget(7, 8));
		
		player1Board.getAllShip();
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
