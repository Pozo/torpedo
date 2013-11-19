package torpedo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShip() {

	}
	@Test(expected = IllegalArgumentException.class)
	public void testShipArgument() {
		new Ship(0);
	}

	@Test
	public void testGetSize() {
		Ship ship = new Ship(2);
		Assert.assertEquals(2, ship.getSize());
	}

}
