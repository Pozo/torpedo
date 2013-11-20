package torpedo.aim;

import java.util.HashSet;

import torpedo.coordinate.Coordinate;

public class CachedRandomTarget implements TargetingSystem {
	private static final HashSet<Coordinate> previusCoordinates = new HashSet<Coordinate>();
	private final RandomTarget randomTarget;
	
	public CachedRandomTarget(int bound) {
		randomTarget = new RandomTarget(bound);
	}
	public static HashSet<Coordinate> getPreviuscoordinates() {
		return previusCoordinates;
	}
	public Coordinate getCoordinate() {
		Coordinate randomCoordinate = randomTarget.generateCoordinate();
		
		while(!previusCoordinates.add(randomCoordinate) && previusCoordinates.size() < randomTarget.getBound() * randomTarget.getBound()) {

			randomCoordinate = randomTarget.generateCoordinate();
		}
		return randomCoordinate;
	}
	public HashSet<Coordinate> getCachedCoordinates() {
		return previusCoordinates;
	}
}
