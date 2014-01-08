package torpedo.gameplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.aim.CachedRandomTarget;
import torpedo.aim.TargetingSystem;
import torpedo.board.ConsolePrinter;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.weapon.SingleTorpedo;
/**
 * ConsoleGame.
 * @author Zoltan_Polgar
 *
 */
public final class ConsoleGame {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleGame.class);
    private static final int BOARD_SIZE = 20;

    private ConsoleGame() { }

    /**
     * main.
     * @param args args
     */
    public static void main(String[] args) {
        SquareGameBoard gameBoard = new SquareGameBoard(BOARD_SIZE);
        gameBoard.initFromFile("ships.txt");

        new ConsolePrinter(gameBoard).print(System.out);
        TargetingSystem targetingSystem = new CachedRandomTarget(gameBoard.getHeight());

        while (!gameBoard.isAllShipWrecked()) {
            SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);

            Coordinate coordinate = targetingSystem.getCoordinate();
            FireResultType fireResult = playerTorpedo.fire(coordinate);

            //targetingSystem.setStateByFireResult(coordinate, fireResult);

            printResult(coordinate, fireResult);

            gameBoard.incrementFireCount();
        }
        LOGGER.info("Number of fire : {}", gameBoard.getFireCount());

    }

    private static void printResult(Coordinate coordinate, FireResultType fire) {
        LOGGER.info("{} at [{},{}]", fire, coordinate.getX(), coordinate.getY());
    }
}
