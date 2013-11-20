package torpedo;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;

public class ShipTest {

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
		coordinates.add(new Coordinate(9, 12));
		
		return coordinates;
	}
	private ArrayList<Coordinate> getSlimShipCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(10, 0));
		coordinates.add(new Coordinate(11, 0));
		
		return coordinates;
	}
	@Test
	public void testShipArrayListOfCoordinate() {
		Ship ship = new Ship(getCoordinates());
		
		HashSet<Coordinate> hashSet = new HashSet<Coordinate>();
		
		hashSet.addAll(ship.getCoordinates());

		Assert.assertFalse(hashSet.addAll(getCoordinates()));
	}
	@Test
	public void getMaxHeight() {
		Ship ship = new Ship(getSlimShipCoordinates());
		
		Assert.assertEquals(1, ship.getMaxHeight());
	}
	@Test
	public void getMaxWidth() {
		Ship ship = new Ship(getSlimShipCoordinates());
		//System.out.println(ship.getMaxWidth());
		Assert.assertEquals(1, ship.getMaxWidth());
	}
	@Test
	public void testShipArrayListOfCoordinateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsWrecked() {
		Ship ship = new Ship(getCoordinates());
		
		ship.addHit(new Coordinate(10, 10));
		ship.addHit(new Coordinate(10, 11));
		ship.addHit(new Coordinate(9, 11));
		
		Assert.assertTrue(ship.isWrecked());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testIsWreckedNotContainedCoordinate() {
		Ship ship = new Ship(getCoordinates());
		
		ship.addHit(new Coordinate(10, 10));
		ship.addHit(new Coordinate(12, 12));
		ship.addHit(new Coordinate(10, 11));
		ship.addHit(new Coordinate(9, 11));
		
		Assert.assertEquals(3, ship.getCoordinates());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAddHitException() {
		Ship ship = new Ship(getSlimShipCoordinates());
		ship.addHit(new Coordinate(0, 0));
	}
	@Test
	public void testAddHitSize() {
		ArrayList<Coordinate> slimShipCoordinates = getSlimShipCoordinates();
		Ship ship = new Ship(slimShipCoordinates);
		ship.addHit(new Coordinate(10, 0));
		
		Assert.assertEquals(slimShipCoordinates.size(), ship.getCoordinates().size());
	}
	@Test
	public void testAddHit() {
		ArrayList<Coordinate> slimShipCoordinates = getSlimShipCoordinates();
		Ship ship = new Ship(slimShipCoordinates);
		ship.addHit(new Coordinate(10, 0));
		
		Assert.assertEquals(slimShipCoordinates.size(), ship.getCoordinates().size());
	}
	@Test
	public void testContainsCoordinate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSize() {
		Ship ship = new Ship(getCoordinates());
		
		Assert.assertEquals(getCoordinates().size(), ship.getCoordinatesNumber());
	}

	@Test
	public void testGetShipCoordinates() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNameNotNull() {
		Ship ship = new Ship(getCoordinates());
		ship.getName().equals("");
	}

}
