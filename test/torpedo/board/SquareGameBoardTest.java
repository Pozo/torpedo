package torpedo.board;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;

public class SquareGameBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	private ArrayList<Coordinate> getCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(1, 1));
		coordinates.add(new Coordinate(1, 2));
		coordinates.add(new Coordinate(2, 2));
		
		return coordinates;
	}
	private ArrayList<Coordinate> getCoordinatesTwo() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(1, 1));
		coordinates.add(new Coordinate(2, 1));
		coordinates.add(new Coordinate(3, 1));
		
		return coordinates;
	}
	@Test
	public void testSquareGameBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllShipCoordinates() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllShip() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlacedShipNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAllShipWrecked() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceShip() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBoardWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBoardHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFireCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAllCoordinateHitted() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementFireCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsCoordinateOnTheBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddShip() {
		fail("Not yet implemented");
	}

}
