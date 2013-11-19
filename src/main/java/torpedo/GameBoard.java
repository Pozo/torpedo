package torpedo;

import java.util.Collection;
import java.util.Set;

import torpedo.coordinate.Coordinate;

public interface GameBoard {
	public Collection<Ship> getAllShip();
	public Set<Coordinate> getAllShipCoordinates();
	public int getPlacedShipNumber();
	public boolean isAllShipWrecked();
	public boolean placeShipTo(Coordinate coordinate, Ship ship);
	public boolean isCoordinateOnTheBoard(Coordinate coordinate);
	public int getBoardWidth();
	public int getBoardHeight();
}
