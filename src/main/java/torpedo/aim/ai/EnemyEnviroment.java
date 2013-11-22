package torpedo.aim.ai;

import java.util.ArrayList;

import torpedo.Ship;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;

public class EnemyEnviroment {
	private ArrayList<Ship> discoveredShips = new ArrayList<Ship>();
	private Ship currentAttackedShip = getEmptyShip();
	
	
	private static Ship getEmptyShip() {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		list.add(new Coordinate(-1,-1));
		return new Ship(list);
	}
	private void truncateCurrentAttackedShip() {
		currentAttackedShip = getEmptyShip();
	}
	public void update(Coordinate previousCoordinate, FireResultType previousHitType) {
		if(previousHitType == FireResultType.HIT) {
			addHitToAttackedShip(previousCoordinate);
		} else if(previousHitType == FireResultType.MISS) {
			
		} else if(previousHitType == FireResultType.SUNK) {
			discoveredShips.add(currentAttackedShip);
			truncateCurrentAttackedShip();
		}
		
	}
	private void addHitToAttackedShip(Coordinate coordinate) {
		currentAttackedShip.addCoordinate(coordinate);
	}
	private TryingDirection analysePreviousShipsShape() {
		for (Ship ship : discoveredShips) {
//			if(ship.getCoordinates())
			
			// verticalRatio = maxHeight + minHeight
			// horizontalkRatio = maxWidth + minWidth
			// if verticalRatio < horizontalRatio the prefferred search method is 
		}
		return null;
	}
}
