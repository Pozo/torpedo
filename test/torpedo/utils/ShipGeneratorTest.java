package torpedo.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.ship.Ship;
import torpedo.ship.providers.RandomShipGenerator;

/**
 * ShipGeneratorTest.
 * @author Zoltan_Polgar
 *
 */
public class ShipGeneratorTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShipIllegalArgument() {
        new RandomShipGenerator(0).getShip();
    }

    @Test
    public void testGetShip() {
        Ship ship = new RandomShipGenerator(4).getShip();
        Assert.assertEquals(1, ship.getCoordinatesNumber());
    }
}
