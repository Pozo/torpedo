package torpedo.board;

import java.util.Collection;
import java.util.Set;

import torpedo.Ship;
import torpedo.coordinate.Coordinate;

public interface GameBoard {
	public Collection<Ship> getAllShip();
	public Set<Coordinate> getAllShipCoordinates();
	public int getPlacedShipNumber();
	public int getFireCount();
	public void incrementFireCount();
	public boolean isAllShipWrecked();
	public boolean placeShip(Ship ship);
	public boolean isCoordinateOnTheBoard(Coordinate coordinate);
	public boolean addShip(Ship ship);
	
	public int getBoardWidth();
	public int getBoardHeight();
}
