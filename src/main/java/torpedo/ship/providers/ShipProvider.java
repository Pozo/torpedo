package torpedo.ship.providers;

import java.util.List;

import torpedo.ship.Ship;
/**
 * ShipProvider.
 * @author Zoltan_Polgar
 *
 */
public interface ShipProvider {
    /**
     * getShips.
     * @return List<Ship>
     */
    List<Ship> getShips();
}
