package torpedo.board;

import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;

/**
 * ShipPlacer.
 * @author Zoltan_Polgar
 *
 */
public class ShipPlacer {
    private GameBoard gameBoard;

    /**
     * ShipPlacer.
     * @param gameBoard gameboard
     */
    public ShipPlacer(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * placeShip.
     * @param ship ship
     * @return successful placed the ship
     */
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
            if (placableShip.hasCommonCoordinate(ship)) {
                throw new IllegalArgumentException("There are some collision !");
            }
        }
    }
}
