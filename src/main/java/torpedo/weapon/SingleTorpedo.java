package torpedo.weapon;

import torpedo.board.GameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.ship.Ship;
/**
 * SingleTorpedo.
 * @author Zoltan_Polgar
 *
 */
public class SingleTorpedo implements Weapon {
    private GameBoard board;

    /**
     * SingleTorpedo.
     * @param board game board
     */
    public SingleTorpedo(GameBoard board) {
        this.board = board;
    }
    /**
     * fire.
     */
    public FireResultType fire(Coordinate fireCoordinate) {
        FireResultType fireResultType = FireResultType.MISS;

        for (Ship ship : board.getAllShip()) {
            if (ship.hasCoordinate(fireCoordinate)) {

                ship.addHit(fireCoordinate);
                if (ship.isWrecked()) {
                    fireResultType = FireResultType.SUNK;
                }
                fireResultType = FireResultType.HIT;
            }
        }
        return fireResultType;
    }

    public FireResultType fire(int x, int y) {
        return this.fire(new Coordinate(x, y));
    }
}
