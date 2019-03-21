import java.util.ArrayList;

/**
 * Main
 */

public class LecteurNiveaux {
    public static void main(String[] args) {
        LevelReader lvlReader = new LevelReader(args[0]);
        Level lvl = lvlReader.readNextLevel();
        ArrayList<Level> lvlTab = new ArrayList<>();
        while (lvl != null) {
            lvlTab.add(lvl);
            lvl = lvlReader.readNextLevel();
        }
        LevelPrinter lvlPrinter = new LevelPrinter(System.out);
        for (Level lv : lvlTab) {
            lvlPrinter.print(lv);
        }
    }
}