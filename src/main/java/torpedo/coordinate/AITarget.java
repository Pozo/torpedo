package torpedo.coordinate;

import java.util.ArrayList;

import torpedo.Ship;
import torpedo.network.protocol.FireResultType;

public class AITarget implements TargetingSystem {
	private enum TryingDirection {
		COUNTERCLOCKWISE,CLOCKWISE;
	}

	private final CachedRandomTarget cachedRandomTarget;
	private final int boardSize;

	private ArrayList<Ship> discoveredShips = new ArrayList<Ship>();
	private Ship currentAttackedShip = getEmptyShip();
	
	private FireResultType previousHitType = FireResultType.MISS;
	private Coordinate previousCoordinate = new Coordinate(-1,-1);
	
	public AITarget(int bound) {
		cachedRandomTarget = new CachedRandomTarget(bound);
		this.boardSize = bound;
	}
	private static Ship getEmptyShip() {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		list.add(new Coordinate(-1,-1));
		return new Ship(list);
	}
	
	public Coordinate getCoordinate() {
		if(previousHitType == FireResultType.MISS || previousHitType == FireResultType.SUNK) {
			return cachedRandomTarget.getCoordinate();
		} else if (previousHitType == FireResultType.HIT){
			return getCoordinateByEnviromentAnalisys();
		}
		return previousCoordinate;
		
	}
	private Coordinate getCoordinateByEnviromentAnalisys() {
		if(previousCoordinate.hasTopNeighbor(boardSize)) {
			
		}
		// TODO Auto-generated method stub
		return previousCoordinate;
		
	}
	private void truncateCurrentAttackedShip() {
		currentAttackedShip = getEmptyShip();
	}
	private boolean isNoAttackedShip() {
		return currentAttackedShip.getCoordinates().isEmpty();
	}
	public void setStateByFireResult(Coordinate previousCoordinate, FireResultType hitType) {
		setPreviousCoordinate(previousCoordinate);
		
		if(hitType == FireResultType.HIT) {
			addHitToAttackedShip(previousCoordinate);
		} else if(hitType == FireResultType.MISS) {
			
		} else if(hitType == FireResultType.SUNK) {
			discoveredShips.add(currentAttackedShip);
			truncateCurrentAttackedShip();
		}
	}
	private void setPreviousCoordinate(Coordinate previousCoordinate) {
		this.previousCoordinate = previousCoordinate;
	}
	private boolean addHitToAttackedShip(Coordinate coordinate) {
		return currentAttackedShip.addCoordinate(coordinate);
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
