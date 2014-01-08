package torpedo.weapon;

import torpedo.coordinate.Coordinate;
import torpedo.network.protocol.FireResultType;

/**
 * Weapon.
 * @author Zoltan_Polgar
 *
 */
public interface Weapon {
    /**
     * fire.
     * @param x x coordinate
     * @param y y coordinate
     * @return FireResultType
     */
    FireResultType fire(int x, int y);
    /**
     * fire.
     * @param coordinate Coordinate
     * @return FireResultType
     */
    FireResultType fire(Coordinate coordinate);
}
