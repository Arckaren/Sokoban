
enum Tile {
	FLOOR(' ', "Ground/ground_04.png"), //
	GOAL('.', "Environment/environment_03.png"), //
	PLAYER('@', "Player/player_21.png"), //
	PLAYER_GOAL('+', ""), //
	WALL('#', "Blocks/block_06.png"), //
	BOX('$', "Crates/crate_09.png"), //
	BOX_GOAL('*', "Crates/crate_39.png");

	private char c;
	private String img;

	Tile(char c, String img) {
		this.c = c;
		this.img = img;
	}

	char getChar() {
		return c;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
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
}
