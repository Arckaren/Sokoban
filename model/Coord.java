package model;

/**
 * Coord
 */
public class Coord {

	private int row;
	private int col;

	public Coord(int row, int col) {
		setLocation(row, col);
	}

	public Coord(Coord c) {
		setLocation(c.row, c.col);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Coord) {
			Coord c = (Coord) o;
			return col == c.col && row == c.row;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(col) * 31 + Integer.hashCode(row);
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	public void setLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * add d to the current coord return the current coord object
	 */
	public Coord add(Direction d) {
		switch (d) {
		case LEFT:
			--col;
			break;
		case RIGHT:
			++col;
			break;
		case BOTTOM:
			++row;
			break;
		case TOP:
			--row;
			break;
		}

		return this;
	}

	/**
	 * create a temporary then add d to it return the temporary
	 */
	public Coord addN(Direction d) {
		Coord tmp = new Coord(this);
		tmp.add(d);
		return tmp;
	}

	@Override
	public String toString() {
		return "(r: " + getRow() + ", c: " + getCol() + ")";
	}

}