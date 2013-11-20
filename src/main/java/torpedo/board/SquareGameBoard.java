package torpedo.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import torpedo.Ship;
import torpedo.coordinate.Coordinate;

public class SquareGameBoard implements GameBoard {
	private final int boardSize;
	private final ShipPlacer shipPlacer;
	private ArrayList<Ship> shipsOnBoard = new ArrayList<Ship>();

	private int firecount;
	
	public SquareGameBoard(int boardSize) {
		if(boardSize<5 || boardSize>25) {
			throw new IllegalArgumentException("The board size must be between 5 andd 25");
		}
		this.boardSize = boardSize;
		this.shipPlacer = new ShipPlacer(this);
	}
	public Set<Coordinate> getAllShipCoordinates() {
		HashSet<Coordinate> coordinates = new HashSet<Coordinate>();
		
		for (Ship ship : shipsOnBoard) {
			coordinates.addAll(ship.getCoordinates());
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
			allWrecked &= ship.isWrecked();
		}
		return allWrecked;
	}
	public boolean placeShip(Ship ship) {
		return shipPlacer.placeShip(ship);
	}
	public int getBoardWidth() {
		return boardSize;
	}
	public int getBoardHeight() {
		return boardSize;
	}
	public int getFireCount() {
		return firecount;
	}
	public boolean isAllCoordinateHitted() {
		return getFireCount()<(getBoardHeight()*getBoardWidth());
	}
	public void incrementFireCount() {
		firecount++;
	}
	public boolean isCoordinateOnTheBoard(Coordinate coordinate) {
		if(coordinate.getX()<getBoardWidth() && coordinate.getY()<getBoardHeight()) {
			return true;
		}
		throw new IllegalArgumentException("The Coordinate must be on the Board !");
	}
	public boolean addShip(Ship ship) {
		return shipsOnBoard.add(ship);
	}
}
