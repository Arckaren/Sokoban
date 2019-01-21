import java.util.ArrayList;

class Level {
    /**
     * LevelLoop
     */
    public interface LevelLoop {
        public void call(Character elem, int row, int col, int nbRow, int nbCol);
    }

    private ArrayList<ArrayList<Character>> tiles;
    private String name;

    public Level() {
        tiles = new ArrayList<>();
    }

    public void forEach(LevelLoop fn) {
        int row = 0;
        int col = 0;

        for (ArrayList<Character> line : tiles) {
            for (Character tile : line) {
                fn.call(tile, row, col, tiles.size(), line.size());
                col++;
            }
            col = 0;
            row++;
        }
    }

    void fixName(String s) {
        name = s;
    }

    void emptyTile(int l, int c) {
        tiles.get(l).set(c, ' ');
    }

    void addWall(int l, int c) {
        tiles.get(l).set(c, '#');
    }

    void addPusher(int l, int c) {
        tiles.get(l).set(c, '@');
    }

    void addCrate(int l, int c) {
        tiles.get(l).set(c, '$');
    }

    void addGoal(int l, int c) {
        tiles.get(l).set(c, '.');
    }

    public void add(char c) {
        if (tiles.size() == 0) {
            addLine();
        }

        tiles.get(tiles.size() - 1).add(c);
    }

    public void addLine() {
        tiles.add(new ArrayList<>());
    }

    int getNbLines() {
        return tiles.size();
    }

    String getName() {
        return name;
    }

    boolean isEmpty(int l, int c) {
        return tiles.get(l).get(c).equals(' ');
    }

    boolean isWall(int l, int c) {
        return tiles.get(l).get(c).equals('#');
    }

    boolean isGoal(int l, int c) {
        return tiles.get(l).get(c).equals('.');
    }

    boolean isPusher(int l, int c) {
        return tiles.get(l).get(c).equals('@');
    }

    boolean isCrate(int l, int c) {
        return tiles.get(l).get(c).equals('$');

    }

}