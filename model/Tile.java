package model;

public enum Tile {
	FLOOR(' '), //
	GOAL('.'), //
	PLAYER('@'), //
	PLAYER_GOAL('+'), //
	WALL('#'), //
	BOX('$'), //
	BOX_GOAL('*');

	private char c;

	Tile(char c) {
		this.c = c;
	}

	public char getChar() {
		return c;
	}

	boolean isBox() {
		return this == BOX || this == BOX_GOAL;
	}

	/**
	 * @return the img
	 */
	public String getImgLabel() {
		return toString();
	}

	/**
	 * fetch the tile matching the char c
	 */
	static Tile valueOf(char c) {
		Tile t = null;
		for (Tile tTmp : Tile.values()) {
			if (tTmp.getChar() == c) {
				t = tTmp;
			}
		}
		if (t == null) {
			throw new IllegalArgumentException("'" + c + "': no tile found");
		}

		return t;
	}

	public boolean isEmptyGoal() {
		return this == Tile.GOAL || this == Tile.PLAYER_GOAL;
	}
}
