package torpedo;

import torpedo.aim.ExactTarget;
import torpedo.aim.TargetingSystem;
import torpedo.board.GameBoard;
import torpedo.board.SquareGameBoard;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;


public class SingleTorpedo implements Torpedo {
	private GameBoard board;
	
	public SingleTorpedo(SquareGameBoard board) {
		this.board = board;
	}
	
	public FireResultType fire(TargetingSystem targetingSystem) {
		Coordinate fireCoordinate = targetingSystem.getCoordinate();
		
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
		return this.fire(new ExactTarget(x, y));
	}
}
