package torpedo.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.Ship;
import torpedo.aim.RandomTarget;
import torpedo.coordinate.Coordinate;
import torpedo.utils.ShipFromFile;
import torpedo.utils.ShipRandomly;

public class SquareGameBoard implements GameBoard {
	private static final Logger logger = LoggerFactory.getLogger(SquareGameBoard.class);
	
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
	public int getWidth() {
		return boardSize;
	}
	public int getHeight() {
		return boardSize;
	}
	public int getFireCount() {
		return firecount;
	}
	public boolean isAllCoordinateHitted() {
		return getFireCount()<(getHeight()*getWidth());
	}
	public void incrementFireCount() {
		firecount++;
	}
	public boolean isCoordinateOnTheBoard(Coordinate coordinate) {
		if(coordinate.getX()<getWidth() && coordinate.getY()<getHeight()) {
			return true;
		}
		throw new IllegalArgumentException("The Coordinate must be on the Board !");
	}
	public boolean addShip(Ship ship) {
		return shipsOnBoard.add(ship);
	}
	public void initRandom(int shipCount) {
		while (getPlacedShipNumber() != shipCount) {
			try {
				Coordinate coordinate = new RandomTarget(getWidth()).getCoordinate();
				Ship ship = new ShipRandomly(ShipRandomly.RECTANGLE_SIZE).getShip();
				ship.transformCoordinates(coordinate);
				
				placeShip(ship);				
			} catch (IllegalArgumentException exception) {
				logger.error(exception.getMessage());
			}
		}
	}
	public void initFromFile(String string) {
		ShipFromFile shipsFromFile = new ShipFromFile(string);
		ArrayList<Ship> ships = shipsFromFile.getShips();
	
		for(Ship ship : ships) {
			boolean success = false;
			while(!success) {
				try {
					Coordinate coordinate = new RandomTarget(getWidth()).getCoordinate();
					ship.transformCoordinates(coordinate);

					success = placeShip(ship);
				} catch (IllegalArgumentException exception) {
					logger.error(exception.getMessage());
				}
			}
		}
	}
}
