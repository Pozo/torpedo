package torpedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import torpedo.coordinate.Coordinate;

public class Ship {
	private String name;
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
		shipCoordinates.put(coordinate, true);
	}
	public boolean containsCoordinate(Coordinate lookingForThisCoordinate) {
		return shipCoordinates.keySet().contains(lookingForThisCoordinate);
	}
	public int getSize() {
		return shipCoordinates.size();
	}

	public Set<Coordinate> getShipCoordinates() {
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
}
