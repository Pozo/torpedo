package torpedo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import torpedo.coordinate.Coordinate;

public class SquareGameBoard implements GameBoard {
	private final int boardSize;
	
	private ArrayList<Ship> shipsOnBoard = new ArrayList<Ship>();
	
	public SquareGameBoard(int boardSize) {
		this.boardSize = boardSize;
	}
	public Set<Coordinate> getAllShipCoordinates() {
		HashSet<Coordinate> coordinates = new HashSet<Coordinate>();
		
		for (Ship ship : shipsOnBoard) {
			coordinates.addAll(ship.getShipCoordinates());
		}
		
		return coordinates;
	}
	public Collection<Ship> getAllShip() {
		return shipsOnBoard;
	}
	public int getPlacedShipNumber() {
		return shipsOnBoard.size();
	}
	public boolean isAllShipWrecked() {
		boolean allWrecked = true;
		
		for (Ship ship : shipsOnBoard) {
			System.out.println("--");
			System.out.println(ship.isWrecked());
			System.out.println("--");
			allWrecked &= ship.isWrecked();
		}
		return allWrecked;
	}
	public boolean placeShipTo(Coordinate coordinate, Ship ship) {
		validateShipCoordinate(coordinate);
		
		if(!shipsOnBoard.contains(ship)) {
			return shipsOnBoard.add(ship);
		} else {
			return false;
		}
	}
	private void validateShipCoordinate(Coordinate coordinate) {
		isCoordinateOnTheBoard(coordinate);
		isThereAnyCollision(coordinate);
	}
	private void isThereAnyCollision(Coordinate coordinate) {
		for (Ship ship : shipsOnBoard) {
			if(ship.containsCoordinate(coordinate)) {
				throw new IllegalArgumentException("There are some collision !");
			}
		}
	}
	public boolean isCoordinateOnTheBoard(Coordinate fireCoordinate) {
		if(fireCoordinate.getX() <= this.getBoardWidth() && fireCoordinate.getY() <= this.getBoardHeight()) {
			return true;
		}
		throw new IllegalArgumentException("The hit must be on the Board !");
	}
	public int getBoardWidth() {
		return boardSize;
	}
	public int getBoardHeight() {
		return boardSize;
	}
}
