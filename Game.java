import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Game
 */
public class Game {

	public static void main(String[] args) {
		FileReader reader;
		File fich = new File(System.getProperty("user.home"), ".armoroides");

		try {
			reader = new FileReader("defaut.cfg");
			Properties prop = new Properties();
			prop.load(reader);
			if (fich.exists()) {
				reader.close();
				reader = new FileReader(fich);
				prop.load(reader);
				reader.close();
			}

			System.out.println("Property Sequence: " + prop.getProperty("Sequence"));
			System.out.println("Property Laboule: " + prop.getProperty("Laboule"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}