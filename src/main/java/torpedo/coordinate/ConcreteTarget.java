package torpedo.coordinate;

public class ConcreteTarget implements TargetingSystem  {
	private final int x;
	private final int y;
	
	public ConcreteTarget(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate getCoordinate() {
		return new Coordinate(x, y);
	}

}
