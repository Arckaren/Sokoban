import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * LevelReader
 */
public class LevelReader {
    private BufferedReader file;

    public LevelReader(String fileName) {
        try {
            file = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Level readNextLevel() {
        if (file != null) {
            Level lvl = new Level();
            try {
                String line;
                while (((line = file.readLine()) != null) && line.trim().charAt(0) != ';') {
                    // tant qu'on est pas arrivé à la fin du niveau
                    lvl.addLine();
                    // on rajoute une ligne
                    for (char c : line.toCharArray()) {
                        lvl.add(c);
                        // on rajoute les caractères un par un
                    }
                }
                if (line == null) {
                    return null;
                    // on est arrivé à la fin
                } else {
                    lvl.fixName(line.substring(1).trim());
                    // sinon on rajoute le nom
                    file.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
            return lvl;
        } else {
            return null;
        }
    }
}