package torpedo;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.aim.ExactTarget;
import torpedo.board.SquareGameBoard;
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
		
		return coordinates;
	}
	@Test
	public void testFireHit() {
		int boardSize = 20;
		
		int fireCoordinateX = 5;
		int fireCoordinateY = 5;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		Coordinate coordinate = new Coordinate(fireCoordinateX, fireCoordinateY);
		Ship ship = new Ship(getCoordinates());
		ship.transformCoordinates(coordinate);
		board.placeShip(ship);
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		ExactTarget targetingSystem = new ExactTarget(fireCoordinateX+10, fireCoordinateY+10);
		Assert.assertTrue((playerTorpedoTest.fire(targetingSystem.getCoordinate())) == FireResultType.HIT);
	}
	@Test
	public void testFireHitWreckedStatus() {
		int boardSize = 20;
		
		int fireCoordinateX = 4;
		int fireCoordinateY = 4;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		
		Coordinate coordinate = new Coordinate(fireCoordinateX, fireCoordinateY);
		
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(14, 14));
		
		Ship ship = new Ship(coordinates);
		ship.transformCoordinates(coordinate);
		board.placeShip(ship);
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		ExactTarget targetingSystem = new ExactTarget(fireCoordinateX, fireCoordinateY);
		
		playerTorpedoTest.fire(targetingSystem.getCoordinate());
		
		targetingSystem = new ExactTarget(14+fireCoordinateX, 14+fireCoordinateY);
		playerTorpedoTest.fire(targetingSystem.getCoordinate());
		
		Assert.assertTrue((playerTorpedoTest.fire(targetingSystem.getCoordinate())) == FireResultType.SUNK );
	}
	@Test
	public void testFireMiss() {
		int boardSize = 20;
		
		int fireCoordinateX = 4;
		int fireCoordinateY = 4;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		
		Coordinate coordinate = new Coordinate(fireCoordinateX, fireCoordinateY);
		Ship ship = new Ship(getCoordinates());
		ship.transformCoordinates(coordinate);
		board.placeShip(ship);
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);

		Assert.assertTrue((playerTorpedoTest.fire(new ExactTarget(fireCoordinateX+1, fireCoordinateY).getCoordinate())) == FireResultType.MISS );
	}
	@Test(expected = IllegalArgumentException.class)
	public void testFireOutOfBoard() {
		int boardSize = 20;
		
		int fireCoordinateX = boardSize+1;
		int fireCoordinateY = 11;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		Coordinate coordinate = new Coordinate(fireCoordinateX, fireCoordinateY);
		Ship ship = new Ship(getCoordinates());
		ship.transformCoordinates(coordinate);
		board.placeShip(ship);
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		Assert.assertTrue((playerTorpedoTest.fire(new ExactTarget(fireCoordinateX+1, fireCoordinateY).getCoordinate())) == FireResultType.MISS );
	
	}
}
