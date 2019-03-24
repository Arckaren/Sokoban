package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * LevelReader
 */
public class LevelReader {
    private Scanner file;

    public LevelReader(String fileName) {
        try {
            file = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LevelReader(InputStream is) {
        file = new Scanner(is);
    }

    public Level readNextLevel() {
        if (file != null) {
            Level lvl = new Level();
            try {
                String line;
                while (((line = file.nextLine()) != null) && line.trim().charAt(0) != ';') {
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
                    lvl.finishLevel();
                    // sinon on rajoute le nom
                    file.nextLine();
                }
            } catch (NoSuchElementException e) {
                return null;
            }
            return lvl;
        } else {
            return null;
        }
    }
}