package torpedo.coordinate;

import java.util.List;

public class CoordinateArrayUtils {
	public enum Action {
		MOST_LEFT,MOST_RIGT,MOST_UPPER,MOST_LOWER;
	}
	
	private final List<Coordinate> coordinates;
	
	public CoordinateArrayUtils(List<Coordinate> list) {
		this.coordinates = list;
	}
	
	public int getFarestCoordinateBy(Action action) {
		if(coordinates.isEmpty()) {
			throw new IllegalArgumentException("A ship size must be at least 1 !");
		}
		Coordinate mostCoordinate = coordinates.get(0);
		int retval = 0;
		
		for (Coordinate coordinate : coordinates) {
			if(action.equals(Action.MOST_LEFT)) {
				retval = mostCoordinate.getX();
				
				if(coordinate.getX()< mostCoordinate.getX()) {
					mostCoordinate = coordinate;
					retval = mostCoordinate.getX();
				}
			} else if(action.equals(Action.MOST_RIGT)) {
				retval = mostCoordinate.getX();
				
				if(coordinate.getX()> mostCoordinate.getX()) {
					mostCoordinate = coordinate;
					retval = mostCoordinate.getX();
				}
			} else if(action.equals(Action.MOST_UPPER)) {
				retval = mostCoordinate.getY();
				
				if(coordinate.getY()> mostCoordinate.getY()) {
					mostCoordinate = coordinate;
					retval = mostCoordinate.getY();
				}
			} else if(action.equals(Action.MOST_LOWER)) {
				retval = mostCoordinate.getY();
				
				if(coordinate.getY()< mostCoordinate.getY()) {
					mostCoordinate = coordinate;
					retval = mostCoordinate.getY();
				}
			} else {
				throw new IllegalArgumentException("Unknown Action");
			}
		}
		return retval;
	}
}
