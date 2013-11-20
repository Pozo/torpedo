package torpedo;

import torpedo.coordinate.Coordinate;

public class ShipPlacer {
	private GameBoard gameBoard;

	public ShipPlacer(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public boolean placeShipTo(Coordinate placeHere, Ship ship) {
		ship.transformCoordinates(placeHere);
		validateShipCoordinates(ship);
		
		if(!gameBoard.getAllShip().contains(ship)) {
			return gameBoard.addShip(ship);
		} else {
			return false;
		}
	}
	private void validateShipCoordinates(Ship ship) {
		//CoordinateOnTheBoard(coordinate);
		canPlaceToBoard(ship);
		isThereAnyCollision(ship);
	}
	private void canPlaceToBoard(Ship ship) {
		for (Coordinate coordinate : ship.getCoordinates()) {
			isCoordinateOnTheBoard(coordinate);
		}
	}
	/*
	private void canPlaceToBoard(Coordinate coordinate, Ship ship) {
		if((coordinate.getX() + ship.getMaxWidth()) >= gameBoard.getBoardWidth()-1) {
			throw new IllegalArgumentException(String.format("Cant place ship next to the board ! %s %s",coordinate, ship));
		}
		if((coordinate.getY() + ship.getMaxHeight()) >= gameBoard.getBoardHeight()) {
			throw new IllegalArgumentException(String.format("Cant place ship next to the board ! %s %s",coordinate, ship));
		}
		
	}
	*/
	private void isThereAnyCollision(Ship placableShip) {
		for (Ship ship : gameBoard.getAllShip()) {
			if(ship.hasCommonCoordinate(placableShip)) {
				throw new IllegalArgumentException("There are some collision !");
			}
		}
	}
	public boolean isCoordinateOnTheBoard(Coordinate fireCoordinate) {
		if(fireCoordinate.getX() < gameBoard.getBoardWidth() && fireCoordinate.getY() < gameBoard.getBoardHeight()) {
			return true;
		}
		throw new IllegalArgumentException("The hit must be on the Board !");
	}
}
