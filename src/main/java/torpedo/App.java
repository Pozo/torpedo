package torpedo;

import topedo.utils.GameBoardPrinter;
import topedo.utils.ShipGenerator;
import torpedo.coordinate.CachedRandomTarget;
import torpedo.coordinate.Coordinate;
import torpedo.coordinate.RandomTarget;



public class App {
	private static final int BOARD_SIZE = 8;
	
	private static final int DEFAULT_SHIP_NUMBER = 2;
	
	public static void main(String[] args) {
		SquareGameBoard player1Board = new SquareGameBoard(BOARD_SIZE);
		initializePlayerBoard(player1Board, DEFAULT_SHIP_NUMBER);
		
		int fireCount = 0;
		
		new GameBoardPrinter(player1Board).print();

		while(!player1Board.isAllShipWrecked()) {
			SingleTorpedo playerTorpedo = new SingleTorpedo(player1Board);
			
			HitType fire = playerTorpedo.fire(new CachedRandomTarget(BOARD_SIZE));
			
			if(fire == HitType.HIT) {
				System.out.println("HIT! ");
			} else if(fire == HitType.MISS) {
				System.out.println("MISS! ");
			} else if(fire == HitType.HIT_AND_WRECKED) {
				System.out.println("Wrecked! ");
			}
			
			fireCount++;
		}
		System.out.println("Number of fire : " + fireCount);

	}
	public static void initializePlayerBoard(SquareGameBoard playerBoard, int shipCount) {
		while (playerBoard.getPlacedShipNumber() != shipCount) {
			try {
				Coordinate coordinate = new RandomTarget(BOARD_SIZE).getCoordinate();
				Ship ship = ShipGenerator.getShip(BOARD_SIZE);
				playerBoard.placeShipTo(coordinate, ship);				
			} catch (IllegalArgumentException exception) {
				
			}

		}
	}
}

