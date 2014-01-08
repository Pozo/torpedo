package torpedo.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.aim.CachedRandomTarget;
import torpedo.aim.TargetingSystem;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.weapon.SingleTorpedo;

class MinerUi extends JFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinerUi.class);
    private static final int BOARD_SIZE = 20;

    public MinerUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Example GUI");
        this.setSize(1200, 400);
        setLayout(new GridLayout(1, 2, 4, 4));

        //int boardSize = 20;

        //this.add(new PlayerBoard(boardSize));
        //this.add(new PlayerBoard(boardSize));

        startGame();
    }

    private void startGame() {
        SquareGameBoard gameBoard = new SquareGameBoard(BOARD_SIZE);
        gameBoard.initFromFile("ships.txt");
        PlayerBoard comp = new PlayerBoard(gameBoard);
        this.add(comp);
        //new ConsolePrinter(gameBoard).print();
        TargetingSystem targetingSystem = new CachedRandomTarget(gameBoard.getHeight());

        while (!gameBoard.isAllShipWrecked()) {
            SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);
            Coordinate coordinate = targetingSystem.getCoordinate();

            FireResultType fire = playerTorpedo.fire(coordinate);

            if (fire == FireResultType.HIT) {
                LOGGER.warn("HIT! ");
                comp.drawHitAt(coordinate);
            } else if (fire == FireResultType.MISS) {
                LOGGER.warn("MISS! ");
            } else if (fire == FireResultType.SUNK) {
                LOGGER.warn("Wrecked! ");
            }
            gameBoard.incrementFireCount();
        }
        LOGGER.info("Number of fire : {}", gameBoard.getFireCount());
    }

    public static void main(String[] args) {
        MinerUi minerUi = new MinerUi();
        minerUi.setVisible(true);
    }
}
