package torpedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import torpedo.coordinate.Coordinate;
import torpedo.coordinate.CoordinateArrayUtils;
import torpedo.coordinate.CoordinateArrayUtils.Action;

public class Ship {
	private String name = "";
	private final HashMap<Coordinate, Boolean> shipCoordinates = new HashMap<Coordinate,Boolean>();
	
	public Ship(ArrayList<Coordinate> shipPoints) {
		if(shipPoints.size() <=0) {
			throw new IllegalArgumentException("A ship size must be at least 1 !");
		}
		for (Coordinate coordinate : shipPoints) {
			shipCoordinates.put(coordinate, false);
		}
	}
	public Ship(ArrayList<Coordinate> shipPoints, String name) {
		this(shipPoints);
		this.name = name;
	}
	public boolean isWrecked() {
		boolean isWrecked = true;
		for (Entry<Coordinate, Boolean> entry : shipCoordinates.entrySet()) {
			isWrecked &= entry.getValue().booleanValue();
		}
		return isWrecked;
	}

	public void addHit(Coordinate coordinate) {
		if(hasCoordinate(coordinate)) {
			shipCoordinates.put(coordinate, true);
		} else {
			throw new IllegalArgumentException(String.format("Ship is not contain this coordinate: %s", coordinate));
		}
	}
	public boolean hasCoordinate(Coordinate coordinate) {
		return shipCoordinates.keySet().contains(coordinate);
		/*
		for (Coordinate coord : getCoordinates()) {
			if(coordinate.equals(coord)) {
				return true;
			}
		}
		return false;
		*/
	}
	public int getCoordinatesNumber() {
		return shipCoordinates.size();
	}
	private ArrayList<Coordinate> getShipCoordinatesAsList() {
		ArrayList<Coordinate> coodinates = new ArrayList<Coordinate>();
		coodinates.addAll(getCoordinates());
		return coodinates;
	}
	public int getMaxWidth() {
		CoordinateArrayUtils coordinateArrayUtils = new CoordinateArrayUtils(getShipCoordinatesAsList());
		return coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_RIGT) - coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_LEFT)+1;
	}
	public int getMaxHeight() {
		CoordinateArrayUtils coordinateArrayUtils = new CoordinateArrayUtils(getShipCoordinatesAsList());
		return coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_UPPER) - coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_LOWER)+1;		
	}
	public Set<Coordinate> getCoordinates() {
		return shipCoordinates.keySet();
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
		for(Coordinate coordinate : this.getCoordinates()) {
			for (Coordinate otherCoordinate : ship.getCoordinates()) {
				if(coordinate.equals(otherCoordinate)) {
					return true;
				}
			}
		}
		return false;
	}
	public void transformCoordinates(Coordinate origo) {
		for (Coordinate coordinate : getCoordinates()) {
			coordinate.offset(origo);
		}		
	}
	public boolean addCoordinate(Coordinate coordinate) {
		return shipCoordinates.put(coordinate, false);
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
	
}
