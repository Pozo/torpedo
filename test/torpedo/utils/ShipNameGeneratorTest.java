package torpedo.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import torpedo.utils.ShipNameGenerator;

public class ShipNameGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRandomName() {
		Assert.assertNotNull(ShipNameGenerator.getRandomName());
	}

}
