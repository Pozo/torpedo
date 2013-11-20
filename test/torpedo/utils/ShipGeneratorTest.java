package torpedo.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.Ship;
import torpedo.utils.ShipRandomly;

public class ShipGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetShipIllegalArgument() {
		new ShipRandomly(0).getShip();
	}
	@Test
	public void testGetShip() {
		Ship ship = new ShipRandomly(4).getShip();
		Assert.assertEquals(1, ship.getCoordinatesNumber());
	}
}
