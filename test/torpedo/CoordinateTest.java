package torpedo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.Coordinate;

public class CoordinateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
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

}
