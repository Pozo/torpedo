package torpedo;

import java.util.ArrayList;
import java.util.List;

import torpedo.coordinate.Coordinate;
import torpedo.coordinate.CoordinateArrayUtils;
import torpedo.coordinate.CoordinateArrayUtils.Action;

public class Ship {
	private String name = "";
	private ArrayList<Coordinate> shipCoordinates = new ArrayList<Coordinate>();
	
	public Ship(ArrayList<Coordinate> shipPoints) {
		if(shipPoints.size() <=0) {
			throw new IllegalArgumentException("A ship size must be at least 1 !");
		}
		shipCoordinates = shipPoints;
	}
	public Ship(ArrayList<Coordinate> shipPoints, String name) {
		this(shipPoints);
		this.name = name;
	}
	public boolean isWrecked() {
		return shipCoordinates.isEmpty();
	}

	public void addHit(Coordinate coordinate) {
		if(hasCoordinate(coordinate)) {
			for (int i = 0; i < shipCoordinates.size(); i++) {
				if(coordinate.equals(shipCoordinates.get(i))) {
					shipCoordinates.remove(i);
				}
			}
		} else {
			throw new IllegalArgumentException(String.format("Ship is not contain this coordinate: %s", coordinate));
		}
	}
	public boolean hasCoordinate(Coordinate coordinate) {
		for (Coordinate coord : getCoordinates()) {
			if(coordinate.equals(coord)) {
				return true;
			}
		}
		return false;

	}
	public int getCoordinatesNumber() {
		return shipCoordinates.size();
	}
	public int getMaxWidth() {
		CoordinateArrayUtils coordinateArrayUtils = new CoordinateArrayUtils(getCoordinates());
		return coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_RIGT) - coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_LEFT)+1;
	}
	public int getMaxHeight() {
		CoordinateArrayUtils coordinateArrayUtils = new CoordinateArrayUtils(getCoordinates());
		return coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_UPPER) - coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_LOWER)+1;		
	}
	public List<Coordinate> getCoordinates() {
		return shipCoordinates;
	}

	@Override
	public String toString() {
		return getClass().getName()
				+ " {\n\t"
				+ (name != null ? "name: " + name + "\n\t" : "")
				+ (shipCoordinates != null ? "shipCoordinates: "
						+ shipCoordinates : "") + "\n}";
	}
	public String getName() {
		return name;
	}
	public boolean hasCommonCoordinate(Ship ship) {
		for (Coordinate otherCoordinate : ship.getCoordinates()) {
			if(hasCoordinate(otherCoordinate)) {
				return true;
			}
		}
		return false;
	}
	public void transformCoordinates(Coordinate origo) {
		for (Coordinate coordinate : getCoordinates()) {
			coordinate.offset(origo);
		}		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((shipCoordinates == null) ? 0 : shipCoordinates.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shipCoordinates == null) {
			if (other.shipCoordinates != null)
				return false;
		} else if (!shipCoordinates.equals(other.shipCoordinates))
			return false;
		return true;
	}
	public void addCoordinate(Coordinate coordinate) {
		shipCoordinates.add(coordinate);
		
	}
	
}
