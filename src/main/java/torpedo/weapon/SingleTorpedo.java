package torpedo;

import torpedo.board.GameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;


public class SingleTorpedo implements Torpedo {
	private GameBoard board;
	
	public SingleTorpedo(GameBoard board) {
		this.board = board;
	}
	
	public FireResultType fire(Coordinate fireCoordinate) {
		for (Ship ship : board.getAllShip()) {
			if(ship.hasCoordinate(fireCoordinate)) {
			
				ship.addHit(fireCoordinate);
				if(ship.isWrecked()) {
					return FireResultType.SUNK;
				} else {
					return FireResultType.HIT;
				}
			}
		}
		return FireResultType.MISS;
	}

	public FireResultType fire(int x, int y) {
		return this.fire(new Coordinate(x, y));
	}
}
