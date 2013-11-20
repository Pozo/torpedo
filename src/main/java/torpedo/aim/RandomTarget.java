package torpedo.aim;

import java.util.Random;

import torpedo.coordinate.Coordinate;

public class RandomTarget implements TargetingSystem {
	private static Random rand = new Random();
	private final int bound;
	
	public int getBound() {
		return bound;
	}

	public RandomTarget(int bound) {
		this.bound = bound;
	}
	
	public Coordinate getCoordinate() {
		return  generateCoordinate();
	}

	protected Coordinate generateCoordinate() {
		Coordinate randomCoordinate = new Coordinate(rand.nextInt(bound), rand.nextInt(bound));
		return randomCoordinate;
	}
}
