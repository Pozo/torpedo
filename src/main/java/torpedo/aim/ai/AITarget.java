package torpedo.aim.ai;

import torpedo.aim.CachedRandomTarget;
import torpedo.aim.TargetingSystem;
import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
/**
 * AITarget.
 * @author Zoltan_Polgar
 *
 */
public class AITarget implements TargetingSystem {
    private final CachedRandomTarget cachedRandomTarget;
    private final int boardSize;

    private final EnemyEnviroment enemyEnviroment = new EnemyEnviroment();
    private HightLightedCoordinate hightLightedCoordinate = new HightLightedCoordinate();

    private FireResultType previousHitType = FireResultType.MISS;
    private Coordinate previousCoordinate;

    /**
     * AITarget.
     * @param bound bound size
     */
    public AITarget(int bound) {
        cachedRandomTarget = new CachedRandomTarget(bound);
        boardSize = bound;
    }
    /**
     * getCoordinate.
     */
    public Coordinate getCoordinate() {
        Coordinate retval = null;
        if (previousHitType == FireResultType.HIT || hightLightedCoordinate.hasHightLightedCoordinate()) {
            if (hightLightedCoordinate.hasHightLightedCoordinate()) {
                if (hightLightedCoordinate.hasAvailableTargetYet()) {
                    Coordinate coordinate = hightLightedCoordinate.getCoordinateByEnviromentAnalisys();
                    cachedRandomTarget.addToCache(coordinate);
                    retval = coordinate;

                } else {
                    hightLightedCoordinate.reset();
                    retval = cachedRandomTarget.getCoordinate();
                }
            } else {
                hightLightedCoordinate.highLight(previousCoordinate, boardSize);
                Coordinate coordinate = hightLightedCoordinate.getCoordinateByEnviromentAnalisys();
                cachedRandomTarget.addToCache(coordinate);
                retval = coordinate;
            }
        } else {
            hightLightedCoordinate.reset();
            retval = cachedRandomTarget.getCoordinate();
        }
        return retval;
    }

    public void setStateByFireResult(Coordinate previousCoordinate, FireResultType hitType) {
        this.previousCoordinate = previousCoordinate;
        previousHitType = hitType;

        enemyEnviroment.update(previousCoordinate, hitType);
    }
}
