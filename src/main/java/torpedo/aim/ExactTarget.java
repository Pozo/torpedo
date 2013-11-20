package torpedo.aim;

import torpedo.coordinate.Coordinate;

public class ExactTarget implements TargetingSystem  {
	private final int x;
	private final int y;
	
	public ExactTarget(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate getCoordinate() {
		return new Coordinate(x, y);
	}

}
