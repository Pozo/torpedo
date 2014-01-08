package torpedo.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.PrintStream;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import torpedo.board.GameBoard;
import torpedo.board.GameBoardPrinter;
import torpedo.coordinate.Coordinate;

/**
 * PlayerBoard.
 * @author Zoltan_Polgar
 *
 */
public class PlayerBoard extends JPanel implements GameBoardPrinter {
    final GameBoard gameBoard;
    final JPanel playerBoard;
    final JList<String> actionList;

    /**
     * PlayerBoard.
     * @param gameBoard gameBoard
     */
    public PlayerBoard(GameBoard gameBoard) {
        setLayout(new GridLayout(1, 2));
        this.gameBoard = gameBoard;
        playerBoard = createPlayerBoard(gameBoard);
        actionList = createPlayerBoardActions();

        this.add(playerBoard);
        this.add(actionList);
    }

    private JPanel createPlayerBoard(GameBoard gameBoard) {
        final int boardSize = gameBoard.getHeight();
        final JPanel playerBoard = new JPanel(new GridLayout(boardSize, boardSize, 1, 1));

        for (int ii = 0; ii < boardSize; ii++) {
            for (int jj = 0; jj < boardSize; jj++) {
                if (gameBoard.getAllShipCoordinates().contains(new Coordinate(jj, ii))) {
                    BoardCell cell = new BoardCell();
                    cell.setPreferredSize(new Dimension(10, 10));
                    cell.setReserved(true);
                    playerBoard.add(cell);
                } else {
                    BoardCell cell = new BoardCell();
                    cell.setPreferredSize(new Dimension(10, 10));
                    cell.setReserved(false);
                    playerBoard.add(cell);
                }
            }
        }
        return playerBoard;
    }

    public void drawHitAt(Coordinate coordinate) {
        Coordinate coord2 = new Coordinate(0, 0);
        //Coordinate coord = new Coordinate(19, 0);
        //int number = ((gameBoard.getHeight()-coordinate.getY())) * (playerBoard.getWidth()- coordinate.getX())+coordinate.getX();
        /*
        BoardCell cell = (BoardCell) playerBoard.getComponent((gameBoard.getHeight()-1-coord.getY())*gameBoard.getHeight()-1-coord.getX());
        cell.setWrecked(true);
         */
        BoardCell cell2 = (BoardCell) playerBoard.getComponent((gameBoard.getHeight() - coord2.getY() - 1)
                * ((gameBoard.getHeight() - coord2.getX()) * coord2.getY() + 1));
        cell2.setWrecked(true);
    }

    private JList<String> createPlayerBoardActions() {
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        JList<String> playerBoardActions = new JList<String>(listModel);
        //listModel.
        listModel.add(0, "a");
        return playerBoardActions;
    }
    public void print(PrintStream printStream) {
        // TODO Auto-generated method stub
    }
}
