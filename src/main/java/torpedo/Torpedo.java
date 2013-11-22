package torpedo;

import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;

public interface Torpedo {
	public FireResultType fire(int x, int y);
	public FireResultType fire(Coordinate coordinate);
}
