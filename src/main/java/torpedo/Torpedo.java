package torpedo;

import torpedo.coordinate.TargetingSystem;

public interface Torpedo {
	public HitType fire(int x, int y);
	public HitType fire(TargetingSystem coordinate);
}
