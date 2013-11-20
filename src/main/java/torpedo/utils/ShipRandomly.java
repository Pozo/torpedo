package torpedo.utils;

import java.util.ArrayList;

import torpedo.Ship;
import torpedo.coordinate.Coordinate;
import torpedo.coordinate.CoordinateArrayUtils;
import torpedo.coordinate.CoordinateArrayUtils.Action;
import torpedo.coordinate.CoordinateUtils;
import torpedo.coordinate.RandomTarget;

public class ShipRandomly {
	public static final int RECTANGLE_SIZE = 4;
	private final int rectangleSize;
	
	public ShipRandomly(int rectangleSize) {
		this.rectangleSize = rectangleSize;
	}
	
	public Ship getShip() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		
		int tryCount = rectangleSize*rectangleSize;
		
		for (int i = 0; i < tryCount; i++) {
			Coordinate coordinate = new RandomTarget(rectangleSize).getCoordinate();
			
			if(coordinates.isEmpty()) {
				coordinates.add(coordinate);
			} else {
				if(validateCoordinateIntegrability(coordinates, coordinate)) {
					coordinates.add(coordinate);
				}
			}
		}

		return new Ship(coordinates, ShipNameGenerator.getRandomName());
	}
	private static boolean validateCoordinateIntegrability(ArrayList<Coordinate> coordinates, Coordinate coordinate) {
		return CoordinateUtils.isNeighborStrict(coordinates.get(coordinates.size()-1), coordinate) 
				&& !coordinates.contains(coordinate) 
				&& isValidCoordinateDistance(coordinates, coordinate, RECTANGLE_SIZE);
	}
	private static boolean isValidCoordinateDistance(ArrayList<Coordinate> coordinates, Coordinate coordinate, int rectangleSize) {
		CoordinateArrayUtils arrayUtils = new CoordinateArrayUtils(coordinates);
		
		if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_LEFT)-coordinate.getX()) > rectangleSize) {
			return false;
		}
		if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_RIGT)-coordinate.getX()) > rectangleSize) {
			return false;
		}
		if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_UPPER)-coordinate.getY()) > rectangleSize) {
			return false;
		}
		if (Math.abs(arrayUtils.getFarestCoordinateBy(Action.MOST_LOWER)-coordinate.getY()) > rectangleSize) {
			return false;
		}
		return true;
	}
}
