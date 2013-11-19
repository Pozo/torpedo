package torpedo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;

public class PlayerTorpedoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFireHit() {
		int boardSize = 20;
		
		int fireCoordinateX = 11;
		int fireCoordinateY = 6;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), new Ship(1));
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		Assert.assertTrue(playerTorpedoTest.fire(new Coordinate(fireCoordinateX, fireCoordinateY)));
	}
	@Test
	public void testFireMiss() {
		int boardSize = 20;
		
		int fireCoordinateX = 11;
		int fireCoordinateY = 11;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), new Ship(1));
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);

		Assert.assertFalse(playerTorpedoTest.fire(new Coordinate(fireCoordinateX+1, fireCoordinateY)));		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testFireOutOfBoard() {
		int boardSize = 20;
		
		int fireCoordinateX = boardSize+1;
		int fireCoordinateY = 11;
		
		SquareGameBoard board = new SquareGameBoard(boardSize);
		board.placeShipTo(new Coordinate(fireCoordinateX, fireCoordinateY), new Ship(1));
	
		SingleTorpedo playerTorpedoTest = new SingleTorpedo(board);
		Assert.assertFalse(playerTorpedoTest.fire(new Coordinate(fireCoordinateX+1, fireCoordinateY)));		
	}
}
