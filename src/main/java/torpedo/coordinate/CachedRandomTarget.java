package torpedo.coordinate;

import java.util.HashSet;

public class CachedRandomTarget extends RandomTarget {
	private static final HashSet<Coordinate> previusCoordinates = new HashSet<Coordinate>();

	public CachedRandomTarget(int bound) {
		super(bound);
	}
	public static HashSet<Coordinate> getPreviuscoordinates() {
		return previusCoordinates;
	}
	@Override
	public Coordinate getCoordinate() {
		Coordinate randomCoordinate = generateCoordinate();
		
		while(!previusCoordinates.add(randomCoordinate)) {

			randomCoordinate = generateCoordinate();
		}
		return randomCoordinate;
	}
}
