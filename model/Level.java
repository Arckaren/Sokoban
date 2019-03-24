package model;

import java.util.ArrayList;

public class Level {
    /**
     * LevelLoop
     */
    public interface LevelLoop {
        public void call(Tile elem, int row, int col, int nbRow, int nbCol);
    }

    private ArrayList<ArrayList<Tile>> tiles;
    private String name;

    public Level() {
        tiles = new ArrayList<>();
    }

    public void forEach(LevelLoop fn) {
        int row = 0;
        int col = 0;

        for (ArrayList<Tile> line : tiles) {
            for (Tile tile : line) {
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

    public void add(char c) {
        if (tiles.size() == 0) {
            addLine();
        }
        tiles.get(tiles.size() - 1).add(Tile.valueOf(c));
    }

    public void addLine() {
        tiles.add(new ArrayList<>());
    }

    public void finishLevel() {
        int maxNbCol = 0;
        for (ArrayList<Tile> l : tiles) {
            if (maxNbCol < l.size()) {
                maxNbCol = l.size();
            }
        }

        for (ArrayList<Tile> l : tiles) {
            for (int i = l.size(); i < maxNbCol; i++) {
                l.add(Tile.FLOOR);
            }
        }
    }

    public int getNbRows() {
        return tiles.size();
    }

    public int getNbCols() {
        return tiles.get(0).size();
    }

    public String getName() {
        return name;
    }

    public Tile get(int row, int col) {
        return tiles.get(row).get(col);
    }

}