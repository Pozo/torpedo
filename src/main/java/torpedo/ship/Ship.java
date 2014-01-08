package torpedo.ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import torpedo.coordinate.Coordinate;
import torpedo.coordinate.CoordinateArrayUtils;
import torpedo.coordinate.CoordinateArrayUtils.Action;

/**
 * Ship.
 * @author Zoltan_Polgar
 *
 */
public class Ship {
    private List<Coordinate> shipCoordinates = new ArrayList<Coordinate>();

    /**
     * Ship.
     * @param shipPoints shipPoints
     */
    public Ship(List<Coordinate> shipPoints) {
        if (shipPoints.size() <= 0) {
            throw new IllegalArgumentException("A ship size must be at least 1 !");
        }
        shipCoordinates = shipPoints;
    }

    public boolean isWrecked() {
        return shipCoordinates.isEmpty();
    }
    /**
     * addHit.
     * @param coordinate coordinate
     */
    public void addHit(Coordinate coordinate) {
        if (hasCoordinate(coordinate)) {
            for (int i = 0; i < shipCoordinates.size(); i++) {
                if (coordinate.equals(shipCoordinates.get(i))) {
                    shipCoordinates.remove(i);
                }
            }
        } else {
            throw new IllegalArgumentException(String.format("Ship is not contain this coordinate: %s", coordinate));
        }
    }
    /**
     * hasCoordinate.
     * @param coordinate coordinate
     * @return has coordinate
     */
    public boolean hasCoordinate(Coordinate coordinate) {
        return getCoordinates().contains(coordinate);
    }

    public int getCoordinatesNumber() {
        return shipCoordinates.size();
    }
    /**
     * getMaxWidth.
     * @return max width
     */
    public int getMaxWidth() {
        CoordinateArrayUtils coordinateArrayUtils = new CoordinateArrayUtils(getCoordinates());
        return coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_RIGT) - coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_LEFT) + 1;
    }
    /**
     * getMaxHeight.
     * @return max height
     */
    public int getMaxHeight() {
        CoordinateArrayUtils coordinateArrayUtils = new CoordinateArrayUtils(getCoordinates());
        return coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_UPPER) - coordinateArrayUtils.getFarestCoordinateBy(Action.MOST_LOWER) + 1;
    }

    public List<Coordinate> getCoordinates() {
        return shipCoordinates;
    }

    @Override
    public String toString() {
        return getClass().getName() + " {\n\t" + (shipCoordinates != null ? "shipCoordinates: " + shipCoordinates : "") + "\n}";
    }
    /**
     * hasCommonCoordinate.
     * @param ship ship
     * @return has common coordinate
     */
    public boolean hasCommonCoordinate(Ship ship) {
        return !Collections.disjoint(ship.getCoordinates(), getCoordinates());
    }

    public void transformCoordinates(Coordinate origo) {
        for (Coordinate coordinate : getCoordinates()) {
            coordinate.offset(origo);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (shipCoordinates == null ? 0 : shipCoordinates.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ship other = (Ship) obj;
        if (shipCoordinates == null) {
            if (other.shipCoordinates != null) {
                return false;
            }
        } else if (!shipCoordinates.equals(other.shipCoordinates)) {
            return false;
        }
        return true;
    }

    public void addCoordinate(Coordinate coordinate) {
        shipCoordinates.add(coordinate);

    }

}
