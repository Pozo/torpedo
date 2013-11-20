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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
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
	public boolean hasLeftNeighbor(int boardWidth) {
		if(this.getY() > 0 && boardWidth>0 ) {
			return true;
		}
		return false;
	}
	public boolean hasRightNeighbor(int boardWidth) {
		if(this.getY()< boardWidth-1 && boardWidth>0) {
			return true;
		}
		return false;
	}
	public boolean hasTopNeighbor(int boardHeight) {
		if(this.getX() > 0 && boardHeight>0) {
			return true;
		}
		return false;
	}
	public boolean hasBottomNeighbor(int boardHeight) {
		if(this.getX()<boardHeight-1) {
			return true;
		}
		return false;
	}
}
