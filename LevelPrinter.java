import java.io.OutputStream;
import java.io.PrintStream;

/**
 * LevelPrinter
 */
public class LevelPrinter {
    private PrintStream out;

    public LevelPrinter(OutputStream out) {
        this.out = new PrintStream(out);
    }

    public void print(Level lvl) {
        lvl.forEach((Tile elem, int row, int col, int nbRow, int nbCol) -> {
            out.print(elem.getChar());
            if (col == nbCol - 1) {
                out.println();
            }
        });

        out.println("; " + lvl.getName());
        out.println();
    }

}