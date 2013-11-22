package torpedo.aim.ai;

import torpedo.aim.CachedRandomTarget;
import torpedo.aim.TargetingSystem;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;

public class AITarget implements TargetingSystem {
	private final CachedRandomTarget cachedRandomTarget;
	private final int boardSize;

	private final EnemyEnviroment enemyEnviroment = new EnemyEnviroment();
	private HightLightedCoordinate hightLightedCoordinate = new HightLightedCoordinate();
	
	private FireResultType previousHitType = FireResultType.MISS;
	private Coordinate previousCoordinate;
	
	public AITarget(int bound) {
		cachedRandomTarget = new CachedRandomTarget(bound);
		this.boardSize = bound;
	}
	
	public Coordinate getCoordinate() {
		if(previousHitType == FireResultType.HIT || hightLightedCoordinate.hasHightLightedCoordinate()){
			if(hightLightedCoordinate.hasHightLightedCoordinate()) {
				if(hightLightedCoordinate.hasAvailableTargetYet()) {
					Coordinate coordinate = hightLightedCoordinate.getCoordinateByEnviromentAnalisys();
					cachedRandomTarget.AddToCache(coordinate);
					return coordinate;
					
				} else {
					hightLightedCoordinate.reset();
					return cachedRandomTarget.getCoordinate();
				}
			} else {
				hightLightedCoordinate.highLight(previousCoordinate, boardSize);
				Coordinate coordinate = hightLightedCoordinate.getCoordinateByEnviromentAnalisys();
				cachedRandomTarget.AddToCache(coordinate);
				return coordinate;
			}
		} else {
			hightLightedCoordinate.reset();
			return cachedRandomTarget.getCoordinate();
		} 
	}
	public void setStateByFireResult(Coordinate previousCoordinate, FireResultType hitType) {
		this.previousCoordinate = previousCoordinate;
		this.previousHitType = hitType;
		
		enemyEnviroment.update(previousCoordinate, hitType);
	}
}
