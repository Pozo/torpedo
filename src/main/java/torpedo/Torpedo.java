package torpedo;

import torpedo.coordinate.TargetingSystem;
import torpedo.network.protocol.FireResultType;

public interface Torpedo {
	public FireResultType fire(int x, int y);
	public FireResultType fire(TargetingSystem coordinate);
}
