package torpedo.aim.ai;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;
import torpedo.ship.Ship;

/**
 * EnemyEnviroment.
 * @author Zoltan_Polgar
 *
 */
public class EnemyEnviroment {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnemyEnviroment.class);
    private List<Ship> discoveredShips = new ArrayList<Ship>();
    private Ship currentAttackedShip = getEmptyShip();

    private static Ship getEmptyShip() {
        List<Coordinate> list = new ArrayList<Coordinate>();
        list.add(new Coordinate(-1, -1));
        return new Ship(list);
    }

    private void truncateCurrentAttackedShip() {
        currentAttackedShip = getEmptyShip();
    }
    /**
     * update.
     * @param previousCoordinate previous coordinate
     * @param previousHitType previous hit type
     */
    public void update(Coordinate previousCoordinate, FireResultType previousHitType) {
        if (previousHitType == FireResultType.HIT) {
            addHitToAttackedShip(previousCoordinate);
        } else if (previousHitType == FireResultType.MISS) {
            LOGGER.warn(previousHitType.toString());
        } else if (previousHitType == FireResultType.SUNK) {
            discoveredShips.add(currentAttackedShip);
            truncateCurrentAttackedShip();
        }

    }

    private void addHitToAttackedShip(Coordinate coordinate) {
        currentAttackedShip.addCoordinate(coordinate);
    }
    /*
    private TryingDirection analysePreviousShipsShape() {
        for (Ship ship : discoveredShips) {
            //if(ship.getCoordinates())

            // verticalRatio = maxHeight + minHeight
            // horizontalkRatio = maxWidth + minWidth
            // if verticalRatio < horizontalRatio the prefferred search method is
        }
        return null;
    }
    */
}
