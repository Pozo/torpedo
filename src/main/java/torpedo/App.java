package torpedo;

import java.util.ArrayList;

import torpedo.coordinate.CachedRandomTarget;
import torpedo.coordinate.Coordinate;
import torpedo.coordinate.RandomTarget;
import torpedo.network.protocol.FireResultType;
import torpedo.utils.GameBoardPrinter;
import torpedo.utils.ShipFromFile;
import torpedo.utils.ShipRandomly;



public class App {
	private static final int BOARD_SIZE = 20;
	
	private static final int DEFAULT_SHIP_NUMBER = 2;
	
	public static void main(String[] args) {
		SquareGameBoard gameBoard = new SquareGameBoard(BOARD_SIZE);
		//initializeRandomBoard(gameBoard, DEFAULT_SHIP_NUMBER);
		initializeBoardFromFile(gameBoard);
		
		new GameBoardPrinter(gameBoard).print();
		System.out.println(gameBoard.getPlacedShipNumber());
		while(!gameBoard.isAllShipWrecked()) {
			SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);
			
			CachedRandomTarget targetingSystem = new CachedRandomTarget(BOARD_SIZE);
			FireResultType fire = playerTorpedo.fire(targetingSystem);
			
			if(fire == FireResultType.HIT) {
				System.out.println("HIT! ");
			} else if(fire == FireResultType.MISS) {
				//System.out.println("MISS! ");
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
				playerBoard.placeShipTo(coordinate, ship);				
			} catch (IllegalArgumentException exception) {
				//System.out.println(exception.getMessage());
			}

		}
	}
	public static void initializeBoardFromFile(SquareGameBoard playerBoard) {
		ShipFromFile shipsFromFile = new ShipFromFile("ships.txt");
		ArrayList<Ship> ships = shipsFromFile.getShips();
		
	
		for(Ship ship : ships) {
			boolean success = false;
			
			while(!success) {
				try {
					Coordinate coordinate = new RandomTarget(playerBoard.getBoardWidth()).getCoordinate();
					System.out.println(coordinate);
					System.out.println(ship);
					ship.transformCoordinates(coordinate);
					System.out.println(ship);
					success = playerBoard.placeShipTo(coordinate, ship);
				} catch (IllegalArgumentException exception) {
					//System.out.println(exception.getMessage());
				}			
			}
			break;
		}
	}
}

