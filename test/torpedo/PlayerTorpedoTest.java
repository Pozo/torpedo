package torpedo;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.ConcreteTarget;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;

public class PlayerTorpedoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	private ArrayList<Coordinate> getCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(10, 10));
		coordinates.add(new Coordinate(10, 11));
		coordinates.add(new Coordinate(9, 11));
		coordinates.add(new Coordinate(0, 0));
		
		return coordinates;
	}
	@Test
	public void testFireHit() {
		int boardSize = 20;
		
		int fireCoordinateX = 10;
		int fireCoordinateY = 10;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), new Ship(getCoordinates()));
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		Assert.assertTrue((playerTorpedoTest.fire(new ConcreteTarget(fireCoordinateX, fireCoordinateY))) == FireResultType.HIT );
	}
	@Test
	public void testFireHitWreckedStatus() {
		int boardSize = 20;
		
		int fireCoordinateX = 10;
		int fireCoordinateY = 10;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		Ship ship = new Ship(getCoordinates());
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), ship);
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		ConcreteTarget targetingSystem = new ConcreteTarget(fireCoordinateX, fireCoordinateY);
		
		playerTorpedoTest.fire(targetingSystem);
		
		targetingSystem = new ConcreteTarget(0, 0);
		playerTorpedoTest.fire(targetingSystem);
		
		System.out.println(ship);
		
		Assert.assertTrue((playerTorpedoTest.fire(targetingSystem)) == FireResultType.HIT );
	}
	@Test
	public void testFireMiss() {
		int boardSize = 20;
		
		int fireCoordinateX = 11;
		int fireCoordinateY = 11;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), new Ship(getCoordinates()));
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);

		Assert.assertTrue((playerTorpedoTest.fire(new ConcreteTarget(fireCoordinateX+1, fireCoordinateY))) == FireResultType.MISS );
	}
	@Test(expected = IllegalArgumentException.class)
	public void testFireOutOfBoard() {
		int boardSize = 20;
		
		int fireCoordinateX = boardSize+1;
		int fireCoordinateY = 11;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), new Ship(getCoordinates()));
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		Assert.assertTrue((playerTorpedoTest.fire(new ConcreteTarget(fireCoordinateX+1, fireCoordinateY))) == FireResultType.MISS );
	
	}
}
