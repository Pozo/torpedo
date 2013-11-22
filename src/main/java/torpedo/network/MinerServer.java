package torpedo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.Ship;
import torpedo.SingleTorpedo;
import torpedo.aim.RandomTarget;
import torpedo.board.ConsolePrinter;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.network.protocol.MinerProtocol;
import torpedo.network.protocol.Procedures;
import torpedo.network.protocol.RequestValidator;
import torpedo.utils.ShipRandomly;

public class MinerServer extends Server implements MinerProtocol {
	private static final Logger logger = LoggerFactory.getLogger(MinerServer.class);
	private static final int PORT_NUMBER = 9876;
	private static final int DEFAULT_SHIP_NUMBER = 2;
	
	private SquareGameBoard gameBoard;
	
	public static void main(String[] args) {
		new MinerServer(PORT_NUMBER).startListening();
	}
	
	public MinerServer(int listeningPortNumber) {
		super(listeningPortNumber);
	}

	@Override
	protected void processRequest(Socket socket) throws IOException {
		String request = readRequest(socket).toUpperCase();
		
		if(new RequestValidator(request).validate()) {
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
			if(request.startsWith(Procedures.GREETING.name())) {
				
				acceptGreeting(socket, request);
			} else if(request.startsWith(Procedures.FIRE.name())) {
				
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
		
		if(splittedRequest.length == 2) {
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
		
		if(splittedRequest.length == 3) {
			FireResultType fire = doFire(splittedRequest);					
			sendResponse(socket, fire.name().toString().toUpperCase());
			
			if(fire == FireResultType.WIN) {
				logger.info(String.format("Player WIN!!! Your fire count : %d", gameBoard.getFireCount()));
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
		logger.info(String.format("Initialized a new gameboard with size : %d", gameBoard.getWidth()));
		initializePlayerBoard(gameBoard, DEFAULT_SHIP_NUMBER);
		logger.info(String.format("Placed %d ship to board", gameBoard.getPlacedShipNumber()));
		new ConsolePrinter(gameBoard).print();
	}
	public boolean isBoardAvailable() {
		return gameBoard != null;
	}
	public FireResultType fire(int x, int y) {
		if(isBoardAvailable()) {
			SingleTorpedo playerTorpedo = new SingleTorpedo(gameBoard);
			
			FireResultType retval = playerTorpedo.fire(new Coordinate(x,y));

			logger.info(String.format("Fire to : x:%d y:%d and the result is %s", x ,y, retval));
			gameBoard.incrementFireCount();
			
			if(gameBoard.isAllShipWrecked()) {
				return FireResultType.WIN;
			} else {
				return retval;				
			}
		} else {
			throw new IllegalArgumentException("There are no available gameboard, you should start with a 'greeting' request !");
		}
	}
	public static void initializePlayerBoard(SquareGameBoard playerBoard, int shipCount) {
		while (playerBoard.getPlacedShipNumber() != shipCount) {
			try {
				Coordinate coordinate = new RandomTarget(playerBoard.getHeight()).getCoordinate();
				Ship ship = new ShipRandomly(playerBoard.getHeight()).getShip();
				ship.transformCoordinates(coordinate);
				playerBoard.placeShip(ship);				
			} catch (IllegalArgumentException exception) {
				logger.error("Invalid ship placing, retry !");
			}

		}
	}
}
