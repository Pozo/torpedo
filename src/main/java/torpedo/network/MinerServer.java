package torpedo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.aim.RandomTarget;
import torpedo.board.ConsolePrinter;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.network.protocol.MinerProtocol;
import torpedo.network.protocol.Procedures;
import torpedo.network.protocol.RequestValidator;
import torpedo.ship.Ship;
import torpedo.ship.providers.RandomShipGenerator;
import torpedo.weapon.SingleTorpedo;

/**
 * MinerServer.
 * @author Zoltan_Polgar
 *
 */
public class MinerServer extends Server implements MinerProtocol {
    private static final int REQUEST_VALID_LENGTH = 3;

    private static final Logger LOGGER = LoggerFactory.getLogger(MinerServer.class);
    private static final int PORT_NUMBER = 9876;
    private static final int DEFAULT_SHIP_NUMBER = 2;

    private SquareGameBoard gameBoard;

    /**
     * MinerServer.
     * @param listeningPortNumber listening port number
     */
    public MinerServer(int listeningPortNumber) {
        super(listeningPortNumber);
    }

    /**
     * main.
     * @param args args
     */
    public static void main(String[] args) {
        new MinerServer(PORT_NUMBER).startListening();
    }

    @Override
    protected void processRequest(Socket socket) throws IOException {
        String request = readRequest(socket).toUpperCase();

        if (new RequestValidator(request).validate()) {
            processResponse(socket, request);
        } else {
            sendResponse(socket, "Something wrong in your request!");
        }
    }

    private String readRequest(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return br.readLine();
    }

    private void processResponse(Socket socket, String request) throws IOException {
        try {
            if (request.startsWith(Procedures.GREETING.name())) {

                acceptGreeting(socket, request);
            } else if (request.startsWith(Procedures.FIRE.name())) {

                acceptFire(socket, request);
            } else {
                sendResponse(socket, "Unknown procedure !");
            }
        } catch (IllegalArgumentException exception) {
            sendResponse(socket, exception.getMessage());
        }

    }

    private void acceptGreeting(Socket socket, String request) throws IOException {
        String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);

        if (splittedRequest.length == 2) {
            doGreeting(splittedRequest);
            sendResponse(socket, "ok");
        }
    }

    private void doGreeting(String[] splittedRequest) {
        int boardSize = Integer.valueOf(splittedRequest[1]);

        greeting(boardSize);
    }

    private void acceptFire(Socket socket, String request) throws IOException {
        String[] splittedRequest = request.split(MinerProtocol.PROCEDURE_PARAMETER_SEPARATOR);

        if (splittedRequest.length == REQUEST_VALID_LENGTH) {
            FireResultType fire = doFire(splittedRequest);
            sendResponse(socket, fire.name().toString().toUpperCase());

            if (fire == FireResultType.WIN) {
                LOGGER.info(String.format("Player WIN!!! Your fire count : %d", gameBoard.getFireCount()));
                setListening(false);
            }
        }
    }

    private FireResultType doFire(String[] splittedRequest) {
        int xCoordinate = Integer.valueOf(splittedRequest[1]);
        int yCoordinate = Integer.valueOf(splittedRequest[2]);

        FireResultType fire = fire(xCoordinate, yCoordinate);
        return fire;
    }

    public void greeting(int boardSize) {
        gameBoard = new SquareGameBoard(boardSize);
        LOGGER.info(String.format("Initialized a new gameboard with size : %d", gameBoard.getWidth()));
        initializePlayerBoard(gameBoard, DEFAULT_SHIP_NUMBER);
        LOGGER.info(String.format("Placed %d ship to board", gameBoard.getPlacedShipNumber()));
        new ConsolePrinter(gameBoard).print(System.out);
    }

    public boolean isBoardAvailable() {
        return gameBoard != null;
    }

    public FireResultType fire(int x, int y) {
        if (isBoardAvailable()) {
            SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);

            FireResultType retval = playerTorpedo.fire(new Coordinate(x, y));

            LOGGER.info(String.format("Fire to : x:%d y:%d and the result is %s", x, y, retval));
            gameBoard.incrementFireCount();

            if (gameBoard.isAllShipWrecked()) {
                retval = FireResultType.WIN;
            }
            return retval;
        }
        throw new IllegalArgumentException("There are no available gameboard, you should start with a 'greeting' request !");
    }

    public static void initializePlayerBoard(SquareGameBoard playerBoard, int shipCount) {
        while (playerBoard.getPlacedShipNumber() != shipCount) {
            try {
                Coordinate coordinate = new RandomTarget(playerBoard.getHeight()).getCoordinate();
                Ship ship = new RandomShipGenerator(playerBoard.getHeight()).getShip();
                ship.transformCoordinates(coordinate);
                playerBoard.placeShip(ship);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Invalid ship placing, retry !");
            }

        }
    }
}
