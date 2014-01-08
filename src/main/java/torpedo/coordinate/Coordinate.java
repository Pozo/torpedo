package torpedo.coordinate;

/**
 * Coordinate.
 * @author Zoltan_Polgar
 *
 */
public class Coordinate implements Comparable<Coordinate> {
    private final int originX;
    private final int originY;

    private int x;
    private int y;

    /**
     * Coordinate.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinate(int x, int y) {
        originX = x;
        originY = y;

        this.x = x;
        this.y = y;
    }
    /**
     * hasNegativeMember.
     * @return has negative member
     */
    public boolean hasNegativeMember() {
        return getX() < 0 || getY() < 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
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

        Coordinate other = (Coordinate) obj;
        if (x == other.x && y == other.y) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getName() + " {\tx: " + x + "\ty: " + y + "}\n";
    }
    /**
     * compareTo.
     * @param o another Coordinate
     * @return comparing result
     */
    public int compareTo(Coordinate o) {
        int retval = 0;
        if (equals(o)) {
            retval = 0;
        }
        if (hashCode() > o.hashCode()) {
            retval = 1;
        }
        retval = -1;
        return retval;
    }
    /**
     * offset.
     * @param origo origo
     */
    public void offset(Coordinate origo) {
        x = originX + origo.getX();
        y = originY + origo.getY();
    }
    /**
     * hasPossibleLeftNeighbor.
     * @return has left neighbor
     */
    public boolean hasPossibleLeftNeighbor() {
        return getY() > 0;
    }
    /**
     * hasPossibleRightNeighbor.
     * @param boardWidth board width
     * @return has right neighbor
     */
    public boolean hasPossibleRightNeighbor(int boardWidth) {
        return getX() + 1 < boardWidth;
    }
    /**
     * hasPossibleTopNeighbor.
     * @param boardHeight board height
     * @return has top neighbor
     */
    public boolean hasPossibleTopNeighbor(int boardHeight) {
        return getY() + 1 < boardHeight;
    }
    /**
     * hasPossibleBottomNeighbor.
     * @return has bottom neighbor
     */
    public boolean hasPossibleBottomNeighbor() {
        return getY() > 0;
    }
}
