package torpedo;

import torpedo.coordinate.ConcreteTarget;
import torpedo.coordinate.Coordinate;
import torpedo.coordinate.TargetingSystem;
import torpedo.network.protocol.FireResultType;


public class SingleTorpedo implements Torpedo {
	private GameBoard board;
	
	public SingleTorpedo(SquareGameBoard board) {
		this.board = board;
	}
	
	public FireResultType fire(TargetingSystem targetingSystem) {
		Coordinate fireCoordinate = targetingSystem.getCoordinate();

		//if(board.isCoordinateOnTheBoard(fireCoordinate)) {
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
		//}
		return FireResultType.MISS;
	}

	public FireResultType fire(int x, int y) {
		return this.fire(new ConcreteTarget(x, y));
	}
}
