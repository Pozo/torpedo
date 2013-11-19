package torpedo.coordinate;

public class Coordinate implements Comparable<Coordinate> {
	private final int x;
	private final int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
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
		return getClass().getName() + " {\n\tx: " + x + "\n\ty: " + y + "\n}";
	}

	public int compareTo(Coordinate o) {
		System.out.println("asdsad");
		if(this.equals(o)) {
			return 0;
		}
		if(this.hashCode()>o.hashCode()) {
			return 1;
		} else {
			return -1;
		}
	}
}
