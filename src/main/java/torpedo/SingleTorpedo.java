package torpedo;

import torpedo.coordinate.ConcreteTarget;
import torpedo.coordinate.Coordinate;
import torpedo.coordinate.TargetingSystem;


public class SingleTorpedo implements Torpedo {
	private GameBoard board;
	
	public SingleTorpedo(SquareGameBoard board) {
		this.board = board;
	}
	
	public HitType fire(TargetingSystem targetingSystem) {
		Coordinate fireCoordinate = targetingSystem.getCoordinate();
		
		if(board.isCoordinateOnTheBoard(fireCoordinate)) {

			for (Ship ship : board.getAllShip()) {
				if(ship.containsCoordinate(fireCoordinate)) {
					ship.addHit(fireCoordinate);
					if(ship.isWrecked()) {
						return HitType.HIT_AND_WRECKED;
					} else {
						return HitType.HIT;
					}
				}
			}
		}
		return HitType.MISS;
	}

	public HitType fire(int x, int y) {
		return this.fire(new ConcreteTarget(x, y));
	}
}
