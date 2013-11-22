package torpedo.coordinate;


public class Coordinate implements Comparable<Coordinate> {
	private final int originX;
	private final int originY;
	
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.originX = x;
		this.originY = y;
		
		this.x = x;
		this.y = y;
	}
	public boolean hasNegativeMember() {
		if(getX()<0) {
			return true;
		}
		if(getY()<0) {
			return true;
		}
		return false;
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
 		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
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

	public int compareTo(Coordinate o) {
		if(this.equals(o)) {
			return 0;
		}
		if(this.hashCode()>o.hashCode()) {
			return 1;
		} else {
			return -1;
		}
	}
	public void offset(Coordinate origo) {
		this.x = (this.originX + origo.getX());
		this.y = (this.originY + origo.getY());
	}
	public boolean hasPossibleLeftNeighbor(int boardWidth) {
		if(this.getY() > 0) {
			return true;
		}
		return false;
	}
	public boolean hasPossibleRightNeighbor(int boardWidth) {
		if(this.getX()+1 < boardWidth) {
			return true;
		}
		return false;
	}
	public boolean hasPossibleTopNeighbor(int boardHeight) {
		if(this.getY()+1 < boardHeight) {
			return true;
		}
		return false;
	}
	public boolean hasPossibleBottomNeighbor(int boardHeight) {
		if(this.getY() > 0) {
			return true;
		}
		return false;
	}
}
