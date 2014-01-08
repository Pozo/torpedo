package torpedo.board;

import java.io.PrintStream;

import torpedo.coordinate.Coordinate;

/**
 * ConsolePrinter.
 * @author Zoltan_Polgar
 *
 */
public class ConsolePrinter implements GameBoardPrinter {
    private final GameBoard gameBoard;

    /**
     * ConsolePrinter.
     * @param gameBoard gameBoard
     */
    public ConsolePrinter(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * print.
     */
    public void print(PrintStream printStream) {
        for (int i = gameBoard.getWidth() - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j <= gameBoard.getHeight() - 1; j++) {

                if (gameBoard.getAllShipCoordinates().contains(new Coordinate(j, i))) {
                    sb.append("[X]");

                } else {
                    sb.append("[ ]");
                }
            }
            printStream.println(sb.toString());
        }
    }
}
