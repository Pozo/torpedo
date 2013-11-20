package torpedo;

import java.util.ArrayList;

import torpedo.aim.CachedRandomTarget;
import torpedo.aim.RandomTarget;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.utils.GameBoardPrinter;
import torpedo.utils.ShipFromFile;
import torpedo.utils.ShipRandomly;



public class App {
	private static final int BOARD_SIZE = 5;
	
	private static final int DEFAULT_SHIP_NUMBER = 2;
	
	public static void main(String[] args) {
		SquareGameBoard gameBoard = new SquareGameBoard(BOARD_SIZE);
		initializeRandomBoard(gameBoard, DEFAULT_SHIP_NUMBER);
		//initializeBoardFromFile(gameBoard);
		
		new GameBoardPrinter(gameBoard).print();
		
		while(!gameBoard.isAllShipWrecked() && gameBoard.isAllCoordinateHitted()) {
			SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);
			
			CachedRandomTarget targetingSystem = new CachedRandomTarget(gameBoard.getBoardHeight());
			FireResultType fire = playerTorpedo.fire(targetingSystem);
			
			if(fire == FireResultType.HIT) {
				System.out.println("HIT! ");
			} else if(fire == FireResultType.MISS) {
				System.out.println("MISS! ");
			} else if(fire == FireResultType.SUNK) {
				System.out.println("Wrecked! ");
			}
			gameBoard.incrementFireCount();
		}
		System.out.println("Number of fire : " + gameBoard.getFireCount());

	}
	public static void initializeRandomBoard(SquareGameBoard playerBoard, int shipCount) {
		while (playerBoard.getPlacedShipNumber() != shipCount) {
			try {
				Coordinate coordinate = new RandomTarget(playerBoard.getBoardWidth()).getCoordinate();
				Ship ship = new ShipRandomly(ShipRandomly.RECTANGLE_SIZE).getShip();
				ship.transformCoordinates(coordinate);
				playerBoard.placeShip(ship);				
			} catch (IllegalArgumentException exception) {
				System.err.println(exception.getMessage());
			}

		}
	}
	public static void initializeBoardFromFile(SquareGameBoard playerBoard) {
		ShipFromFile shipsFromFile = new ShipFromFile("ships.txt");
		ArrayList<Ship> ships = shipsFromFile.getShips();
	
		for(Ship ship : ships) {
			boolean success = false;
			System.out.println(ship);
			while(!success) {
				try {
					Coordinate coordinate = new RandomTarget(playerBoard.getBoardWidth()).getCoordinate();
					ship.transformCoordinates(coordinate);
					System.out.println(ship);
					success = playerBoard.placeShip(ship);
				} catch (IllegalArgumentException exception) {
					System.err.println(exception.getMessage());
				}
			}
		}
	}
}

