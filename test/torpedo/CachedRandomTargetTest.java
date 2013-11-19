package torpedo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.coordinate.CachedRandomTarget;
import torpedo.coordinate.Coordinate;

public class CachedRandomTargetTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCoordinate() {
		Coordinate coordinate = CachedRandomTarget.getCoordinate(4);
		System.out.println(coordinate);
		Coordinate o = new Coordinate(coordinate.getX(), coordinate.getY());
		
		System.out.println(o);
		
		Assert.assertTrue(CachedRandomTarget.getPreviuscoordinates().contains(o));
	}

}
