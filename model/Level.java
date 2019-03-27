package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Level {
    /**
     * LevelLoop
     */
    public interface LevelLoop {
        public void call(Tile elem, int row, int col, int nbRow, int nbCol);
    }

    private ArrayList<ArrayList<Tile>> tiles;
    private String name;
    private Coord coordPusher;

    public Level() {
        tiles = new ArrayList<>();
        coordPusher = new Coord(-1, -1);
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
        Tile t = Tile.valueOf(c);
        tiles.get(tiles.size() - 1).add(t);
        if (isPusher(t)) {
            coordPusher.setLocation(tiles.size() - 1, tiles.get(tiles.size() - 1).size() - 1);
        }
    }

    private boolean isPusher(Tile t) {
        return t == Tile.PLAYER || t == Tile.PLAYER_GOAL;
    }

    public void addLine() {
        tiles.add(new ArrayList<>());
    }

    /**
     * @return the coordPusher
     */
    public Coord getCoordPusher() {
        return coordPusher;
    }

    public boolean isCompleted() {
        for (ArrayList<Tile> line : tiles) {
            for (Tile tile : line) {
                if (tile == Tile.GOAL) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 
     */
    public boolean isFree(Coord c) {
        try {
            return get(c) == Tile.FLOOR || get(c) == Tile.GOAL;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isAccessible(Coord c, Direction d) {
        try {
            return isFree(c.addN(d)) || (get(c.addN(d)).isBox() && isFree(c.addN(d).add(d)));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Set<Coord> getAccessibles(Coord p) {
        Set<Coord> pts = new HashSet<>();
        for (Direction d : Direction.values()) {
            if (isAccessible(p, d)) {
                pts.add(p.addN(d));
            }
        }
        return pts;
    }

    public Set<Coord> getAccessibles(int row, int col) {
        return getAccessibles(new Coord(row, col));
    }

    private void removePusher() {
        switch (get(coordPusher)) {
        case PLAYER:
            set(coordPusher, Tile.FLOOR);
            break;

        case PLAYER_GOAL:
            set(coordPusher, Tile.GOAL);
            break;
        default:
            break;
        }
    }

    private void placePusher() {
        switch (get(coordPusher)) {
        case FLOOR:
            set(coordPusher, Tile.PLAYER);
            break;

        case GOAL:
            set(coordPusher, Tile.PLAYER_GOAL);
            break;
        default:
            break;
        }
    }

    public void movePusher(Direction d) {
        if (isAccessible(getCoordPusher(), d)) {
            removePusher();
            coordPusher.add(d);
            placePusher();
        } else {
            throw new InvalidMoveException(coordPusher, coordPusher.addN(d));
        }
    }

    public void movePusher(Coord c) {
        if (getAccessibles(getCoordPusher()).contains(c)) {
            removePusher();
            coordPusher = c;
            placePusher();
        } else {
            throw new InvalidMoveException(coordPusher, c);
        }
    }

    public void finishLevelEdit() {
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

    public Tile get(Coord c) {
        return tiles.get(c.getRow()).get(c.getCol());
    }

    private void set(Coord c, Tile t) {
        tiles.get(c.getRow()).set(c.getCol(), t);
    }
}