package torpedo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;

public class SquareGameBoardTest {
	private static final int BOARD_SIZE = 20;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoard() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		Assert.assertEquals(BOARD_SIZE, board.getBoardSize());
	}

	@Test
	public void testAddHit() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		Coordinate coordinate = new Coordinate(3, 4);
		board.addHit(coordinate);		
		
		Coordinate coordinate2 = new Coordinate(3, 4);
		
		Assert.assertTrue(board.getHits().contains(coordinate2));
	}

	@Test
	public void testGetRemainingShipSlotNumber() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		
		int shipLenght = 1;
		
		board.placeShipTo(new Coordinate(3, 4), new Ship(shipLenght));
		
		Assert.assertEquals(shipLenght, board.getRemainingShipSlotNumber());
	}
	@Test
	public void testGetRemainingShipSlotNumberAfterOneFire() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		
		int shipLenght = 1;
		
		board.placeShipTo(new Coordinate(3, 4), new Ship(shipLenght));
		SingleTorpedo playerTorpedo = new SingleTorpedo(board);
		playerTorpedo.fire(new Coordinate(3, 4));
		
		Assert.assertEquals(shipLenght-1, board.getRemainingShipSlotNumber());
	}
	@Test
	public void testGetRemainingShipSlotNumberAfterTwoFireOneMiss() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		
		int shipLenght = 1;
		
		board.placeShipTo(new Coordinate(3, 4), new Ship(shipLenght));		
		board.placeShipTo(new Coordinate(2, 1), new Ship(shipLenght));
		
		SingleTorpedo playerTorpedo = new SingleTorpedo(board);
		playerTorpedo.fire(new Coordinate(3, 4));
		playerTorpedo.fire(new Coordinate(1, 2));
		
		Assert.assertEquals(1, board.getRemainingShipSlotNumber());
	}
	@Test
	public void testIsShipSlopReserved() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		board.placeShipTo(new Coordinate(3, 4), new Ship(1));
		
		Assert.assertTrue(board.isShipSlopReservedAt(new Coordinate(3, 4)));
	}
	@Test
	public void testIsShipSlopNOTReserved() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		board.placeShipTo(new Coordinate(3, 4), new Ship(1));
		
		Assert.assertFalse(board.isShipSlopReservedAt(new Coordinate(3, 6)));		
	}
	@Test
	public void testGetShipCoordinates() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		board.placeShipTo(new Coordinate(3, 4), new Ship(1));
	}

	@Test
	public void testIsAllShipWrecked() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		
		int shipLenght = 1;
		
		board.placeShipTo(new Coordinate(3, 4), new Ship(shipLenght));		
		board.placeShipTo(new Coordinate(2, 1), new Ship(shipLenght));
		
		SingleTorpedo playerTorpedo = new SingleTorpedo(board);
		playerTorpedo.fire(new Coordinate(3, 4));
		playerTorpedo.fire(new Coordinate(2, 1));
		
		Assert.assertTrue(board.isAllShipWrecked());
	}
	@Test
	public void testIsAllShipNOTWrecked() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		
		int shipLenght = 1;
		
		board.placeShipTo(new Coordinate(3, 4), new Ship(shipLenght));		
		board.placeShipTo(new Coordinate(2, 1), new Ship(shipLenght));
		
		SingleTorpedo playerTorpedo = new SingleTorpedo(board);
		playerTorpedo.fire(new Coordinate(3, 4));
		playerTorpedo.fire(new Coordinate(3, 1));
		
		Assert.assertFalse(board.isAllShipWrecked());
	}
	@Test
	public void testPutShipTo() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		Assert.assertTrue(board.placeShipTo(new Coordinate(3, 4), new Ship(1)));		
	}
	@Test
	public void testPutShipToSameCoordinate() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
	
		Assert.assertTrue(board.placeShipTo(new Coordinate(3, 4), new Ship(1)));
		Assert.assertFalse(board.placeShipTo(new Coordinate(3, 4), new Ship(1)));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testPutShipToArguments() {
		SquareGameBoard board = new SquareGameBoard(BOARD_SIZE);
		board.placeShipTo(new Coordinate(BOARD_SIZE+1, 4), new Ship(1));
	}
}
