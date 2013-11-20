package torpedo.board;

import torpedo.Ship;
import torpedo.coordinate.Coordinate;

public class ShipPlacer {
	private GameBoard gameBoard;

	public ShipPlacer(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public boolean placeShip(Ship ship) {
		validateShipCoordinates(ship);
		return gameBoard.addShip(ship);
	}
	private void validateShipCoordinates(Ship ship) {
		//CoordinateOnTheBoard(coordinate);
		canPlaceToBoard(ship);
		isThereAnyCollision(ship);
	}
	private void canPlaceToBoard(Ship ship) {
		for (Coordinate coordinate : ship.getCoordinates()) {
			gameBoard.isCoordinateOnTheBoard(coordinate);
		}
	}
	private void isThereAnyCollision(Ship placableShip) {

		for (Ship ship : gameBoard.getAllShip()) {
			if(placableShip.hasCommonCoordinate(ship)) {
				throw new IllegalArgumentException("There are some collision !");
			}
		}
	}
}
