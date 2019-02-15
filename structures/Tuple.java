package structures;

public class Tuple<X, Y> {
	private final X x;
	private final Y y;

	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * @return the x
	 */
	public X getX() {
		return x;
	}

	/*
	 * @return the y
	 */
	public Y getY() {
		return y;
	}
	
}