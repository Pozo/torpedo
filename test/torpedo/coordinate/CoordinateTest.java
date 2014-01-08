package torpedo.coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * CoordinateTest.
 * @author Zoltan_Polgar
 *
 */
public class CoordinateTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHashCode() {
        Coordinate coordiante = new Coordinate(4, 6);
        Coordinate coordiante2 = new Coordinate(4, 6);

        Assert.assertTrue(coordiante.equals(coordiante2) && coordiante2.equals(coordiante));
        Assert.assertTrue(coordiante.hashCode() == coordiante2.hashCode());
    }

    @Test
    public void testCoordinate() {
        Coordinate coordiante = new Coordinate(4, 6);

        Assert.assertEquals(4, coordiante.getX());
        Assert.assertEquals(6, coordiante.getY());
    }

    @Test
    public void testEqualsObject() {
        Assert.assertTrue(new Coordinate(4, 6).equals(new Coordinate(4, 6)));
    }

    @Test
    public void testEqualsObjectAfterTransform() {
        Coordinate coordiante = new Coordinate(4, 6);
        coordiante.offset(new Coordinate(1, 1));
        Assert.assertTrue(coordiante.equals(new Coordinate(5, 7)));
    }

    @Test
    public void testEqualsObjectAfterValueChange() {
        Assert.assertTrue(new Coordinate(4, 6).equals(new Coordinate(4, 6)));
    }

    @Test
    public void testOverrideValueInHashMap() {
        Set<Coordinate> coordinates = new HashSet<Coordinate>();
        Map<Coordinate, Boolean> coordinatesMap = new HashMap<Coordinate, Boolean>();

        coordinates.contains(new Coordinate(1, 2));
        coordinatesMap.put(new Coordinate(1, 2), false);
        coordinatesMap.put(new Coordinate(1, 2), true);
        Assert.assertEquals(coordinatesMap.size(), 1);
    }
}
