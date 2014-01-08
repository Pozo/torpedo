package torpedo.board;

import java.io.PrintStream;

/**
 * SwingPrinter.
 * @author Zoltan_Polgar
 *
 */
public class SwingPrinter implements GameBoardPrinter {
    private final GameBoard gameBoard;

    /**
     * SwingPrinter.
     * @param gameBoard gameBoard
     */
    public SwingPrinter(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * print.
     */
    public void print() {
        /*
        for (int j = 0; j <= this.gameBoard.getHeight()-1; j++) {

            if(this.gameBoard.getAllShipCoordinates().contains(new Coordinate(j, i))) {


            } else {

            }
        }
         */
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
    public void print(PrintStream printStream) {
        // TODO Auto-generated method stub

    }

}
