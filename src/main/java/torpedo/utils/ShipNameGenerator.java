package torpedo.utils;

import java.util.Random;

public class ShipNameGenerator {
	private final static String[] shipNames = new String[] { "Roz Márta",
			"Finn Igor", "Kinnka Pál", "Megv Erik", "Meta Flóra", "Arany Áron",
			"Moto Zoltán", "Krepp Elek", "Csin Csilla", "Kanya Róbert",
			"Kapa Réka" };
	private static Random rand = new Random();
	private static final int bound = shipNames.length;

	public static String getRandomName() {
		return shipNames[rand.nextInt(bound)];
	}
}
