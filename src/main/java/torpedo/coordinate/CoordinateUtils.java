package torpedo.coordinate;

public class CoordinateUtils {
	public static boolean isNeighborWeak(Coordinate coordinate, Coordinate coordinate1) {
		if(coordinate1.equals(coordinate)) {
			return false;
		}
		if(isXNeighborWeak(coordinate, coordinate1) && isYNeighborWeak(coordinate, coordinate1)) {
			return true;
		}
		return false;		
	}
	public static boolean isNeighborStrict(Coordinate coordinate, Coordinate coordinate1) {
		if(coordinate1.equals(coordinate)) {
			return false;
		}
		
		if(isXNeighborStrict(coordinate, coordinate1) || isYNeighborStrict(coordinate, coordinate1)) {
			return true;
		}
		return false;	
	}
	private static boolean isYNeighborStrict(Coordinate coordinate, Coordinate coordinate1) {
		return (coordinate.getY()-1 == coordinate1.getY() || coordinate.getY()+1 == coordinate1.getY()) 
				&& (coordinate1.getX() == coordinate.getX());
	}

	private static boolean isXNeighborStrict(Coordinate coordinate, Coordinate coordinate1) {
		return (coordinate.getX()-1 == coordinate1.getX() || coordinate.getX()+1 == coordinate1.getX()) 
				&& (coordinate1.getY() == coordinate.getY());
	}
	private static boolean isYNeighborWeak(Coordinate coordinate, Coordinate coordinate1) {
		return coordinate.getY()-1 == coordinate1.getY() || coordinate.getY()+1 == coordinate1.getY();
	}

	private static boolean isXNeighborWeak(Coordinate coordinate, Coordinate coordinate1) {
		return coordinate.getX()-1 == coordinate1.getX() || coordinate.getX()+1 == coordinate1.getX();
	}
}
