package torpedo;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;

/**
 * ShipTest.
 * @author Zoltan_Polgar
 *
 */
public class ShipTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private List<Coordinate> getCoordinates() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(10, 10));
        coordinates.add(new Coordinate(10, 11));
        coordinates.add(new Coordinate(9, 11));
        coordinates.add(new Coordinate(9, 12));

        return coordinates;
    }

    private List<Coordinate> getSlimShipCoordinates() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(10, 0));
        coordinates.add(new Coordinate(11, 0));

        return coordinates;
    }

    @Test
    public void testShipArrayListOfCoordinate() {
        Ship ship = new Ship(getCoordinates());

        Set<Coordinate> hashSet = new HashSet<Coordinate>();

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
        List<Coordinate> slimShipCoordinates = getSlimShipCoordinates();
        Ship ship = new Ship(slimShipCoordinates);
        ship.addHit(new Coordinate(10, 0));

        Assert.assertEquals(slimShipCoordinates.size(), ship.getCoordinates().size());
    }

    @Test
    public void testAddHit() {
        List<Coordinate> slimShipCoordinates = getSlimShipCoordinates();
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

}
