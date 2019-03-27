package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import global.Configuration;

public class Level extends Observable {
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
                if (tile.isEmptyGoal()) {
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

    public HashMap<Coord, Direction> getAccessibles(Coord p) {
        HashMap<Coord, Direction> pts = new HashMap<>();
        for (Direction d : Direction.values()) {
            if (isAccessible(p, d)) {
                pts.put(p.addN(d), d);
            }
        }
        return pts;
    }

    public HashMap<Coord, Direction> getAccessibles(int row, int col) {
        return getAccessibles(new Coord(row, col));
    }

    /**
     * remove the box or the player in c
     */
    private void remove(Coord c) {
        switch (get(c)) {
        case PLAYER:
        case BOX:
            set(c, Tile.FLOOR);
            break;

        case PLAYER_GOAL:
        case BOX_GOAL:
            set(c, Tile.GOAL);
            break;
        default:
            break;
        }
    }

    /**
     * place box or player t on c
     * 
     * @param c
     * @param t
     */
    private void place(Coord c, Tile t) {
        if (t != Tile.PLAYER && t != Tile.BOX) {
            throw new IllegalArgumentException("cannot place a " + t);
        }

        switch (get(c)) {
        case FLOOR:
            set(c, t);
            break;

        case GOAL:
            switch (t) {
            case BOX:
                set(c, Tile.BOX_GOAL);
                break;
            case PLAYER:
                set(c, Tile.PLAYER_GOAL);
                break;
            }
            break;
        default:
            break;
        }
    }

    public void movePusher(Direction d) {
        if (isAccessible(getCoordPusher(), d)) {
            remove(coordPusher);
            Coord oldPusher = new Coord(coordPusher);
            coordPusher.add(d);
            if (get(coordPusher).isBox()) {
                Configuration.getInstance().logger().info("push a box!!!");
                remove(coordPusher);
                Coord to = coordPusher.addN(d);
                place(to, Tile.BOX);
                setChanged();
                notifyObservers(new MoveEvent(coordPusher, to));
            }
            place(coordPusher, Tile.PLAYER);
            setChanged();
            notifyObservers(new MoveEvent(oldPusher, coordPusher));
        } else {
            throw new InvalidMoveException(coordPusher, coordPusher.addN(d));
        }
    }

    public void movePusher(Coord c) {
        HashMap<Coord, Direction> m = getAccessibles(getCoordPusher());
        if (m.containsKey(c)) {
            movePusher(m.get(c));
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

    @Override
    public Level clone() {
        Level tmp = new Level();
        tmp.coordPusher = new Coord(coordPusher);
        tmp.name = name;
        for (ArrayList<Tile> line : tiles) {
            tmp.addLine();
            for (Tile tile : line) {
                tmp.add(tile.getChar());
            }
        }

        return tmp;
    }
}