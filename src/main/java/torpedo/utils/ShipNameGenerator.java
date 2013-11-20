package torpedo.utils;

import java.util.Random;

public class ShipNameGenerator {
	private final static String[] shipNames = new String[] { "Roz M�rta",
			"Finn Igor", "Kinnka P�l", "Megv Erik", "Meta Fl�ra", "Arany �ron",
			"Moto Zolt�n", "Krepp Elek", "Csin Csilla", "Kanya R�bert",
			"Kapa R�ka" };
	private static Random rand = new Random();
	private static final int bound = shipNames.length;

	public static String getRandomName() {
		return shipNames[rand.nextInt(bound)];
	}
}
