package torpedo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.aim.ai.AITarget;
import torpedo.board.ConsolePrinter;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static final int BOARD_SIZE = 20;
	
	public static void main(String[] args) {
		SquareGameBoard gameBoard = new SquareGameBoard(BOARD_SIZE);
		gameBoard.initFromFile("ships.txt");
		
		new ConsolePrinter(gameBoard).print();
		AITarget targetingSystem = new AITarget(gameBoard.getHeight());
		
		while(!gameBoard.isAllShipWrecked()) {
			SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);

			Coordinate coordinate = targetingSystem.getCoordinate();
			FireResultType fireResult = playerTorpedo.fire(coordinate);

			targetingSystem.setStateByFireResult(coordinate, fireResult);
			
			printResult(coordinate, fireResult);

			gameBoard.incrementFireCount();
		}
		logger.info("Number of fire : {}",gameBoard.getFireCount());

	}

	private static void printResult(Coordinate coordinate, FireResultType fire) {
		logger.info("{} at [{},{}]",fire, coordinate.getX(), coordinate.getY());
	}
}

