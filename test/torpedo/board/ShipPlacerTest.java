package torpedo.board;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import torpedo.Ship;
import torpedo.SingleTorpedo;
import torpedo.aim.RandomTarget;
import torpedo.coordinate.Coordinate;

public class ShipPlacerTest {
	private ArrayList<Coordinate> getCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(10, 10));
		coordinates.add(new Coordinate(10, 11));
		coordinates.add(new Coordinate(9, 11));
		coordinates.add(new Coordinate(9, 12));
		
		return coordinates;
	}
	private ArrayList<Coordinate> getSlimShipCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(1, 0));
		coordinates.add(new Coordinate(0, 0));
		
		return coordinates;
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShipPlacer() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceShip() {
		SquareGameBoard gameBoard = new SquareGameBoard(5);
		gameBoard.placeShip(new Ship(getSlimShipCoordinates()));
		
		System.out.println(gameBoard.getPlacedShipNumber());
		System.out.println(new SingleTorpedo(gameBoard).fire(new RandomTarget(1)));
		
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
