package torpedo.aim.ai;

import torpedo.coordinate.Coordinate;


public class HightLightedCoordinate {
	private final Coordinate invalidCoordinate = new Coordinate(-1, -1);
	
	private static final int TOP = 0x1;
	private static final int BOTTOM = 0x2;
	private static final int LEFT = 0x4;
	private static final int RIGHT = 0x8;
	
	private static final int NO_ONE = 0x0;
	
	private int tryed = NO_ONE;
	
	private Coordinate coordinate = invalidCoordinate;
	private int boardSize;

	public void highLight(Coordinate coordinate, int boardSize) {
		this.coordinate = coordinate;
		this.boardSize = boardSize;
	}
	public Coordinate getCoordinateByEnviromentAnalisys() {
		if(coordinate.hasPossibleTopNeighbor(boardSize) && (tryed & TOP) == NO_ONE) {
			coordinate.offset(new Coordinate(0, 1));
			addFlag(TOP);
			
		} else if(coordinate.hasPossibleBottomNeighbor(boardSize) && (tryed & BOTTOM) == NO_ONE) {
			coordinate.offset(new Coordinate(0, -1));
			addFlag(BOTTOM);
			
		} else if(coordinate.hasPossibleRightNeighbor(boardSize) && (tryed & RIGHT) == NO_ONE) {
			coordinate.offset(new Coordinate(1, 0));
			addFlag(RIGHT);
			
		} else if(coordinate.hasPossibleLeftNeighbor(boardSize) && (tryed & LEFT) == NO_ONE) {
			coordinate.offset(new Coordinate(-1, 0));
			addFlag(LEFT);
		}
		return coordinate;
	}
	private void addFlag(int flag) {
		this.tryed |= flag;
		
	}
	public boolean hasAvailableTargetYet() {
		if(coordinate.hasPossibleTopNeighbor(boardSize) && (tryed & TOP) == NO_ONE) {
			return true;
		} 
		if(coordinate.hasPossibleBottomNeighbor(boardSize) && (tryed & BOTTOM) == NO_ONE) {
			return true;
		}
		if(coordinate.hasPossibleLeftNeighbor(boardSize) && (tryed & LEFT) == NO_ONE) {
			return true;
		}
		if(coordinate.hasPossibleRightNeighbor(boardSize) && (tryed & RIGHT) == NO_ONE) {
			return false;
		}
		return false;
	}
	public boolean hasHightLightedCoordinate() {
		if(!this.coordinate.equals(invalidCoordinate)) {
			return true;
		} else {
			return false;
		}
	}
	public void reset() {
		this.coordinate = invalidCoordinate;
		this.tryed = NO_ONE;
	}
}
