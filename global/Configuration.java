package global;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import structures.ArrayListFactory;
import structures.LinkedListFactory;
import structures.ListFactory;

public class Configuration {
	static final String SEQ = "Sequence";
	static final String SEQ_ARRAY = "Tableau";
	static final String SEQ_LIST = "Liste";
	static final String LOG_LEVEL = "LogLevel";

	// Singloton
	private static Configuration config = null;
	// Configuration attributes
	private Properties prop;
	private Logger logger;

	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	public String get(String key) {
		if (prop.containsKey(key)) {
			return prop.getProperty(key);
		} else {
			throw new NoSuchElementException(key + " property does not exist");
		}
	}

	public ListFactory getListFactory() {
		String seq = get(SEQ);
		if (seq.equals(SEQ_LIST)) {
			return new LinkedListFactory();
		} else if (seq.equals(SEQ_ARRAY)) {
			return new ArrayListFactory();
		} else {
			throw new NoSuchElementException(seq + " implementation does not exist");
		}
	}

	public InputStream load(String src) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(src);
	}

	private Configuration() {
		InputStream reader;
		File fich = new File(System.getProperty("user.home"), ".armoroides");

		try {
			reader = load("defaut.cfg");
			prop = new Properties();
			prop.load(reader);
			if (fich.exists()) {
				reader.close();
				reader = new FileInputStream(fich);
				prop.load(reader);
				reader.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Level getLogLevel() {
		return Level.parse(get(LOG_LEVEL));
	}

	public Logger logger() {
		if (logger == null) {
			logger = Logger.getLogger("Sokoban");
			logger.setLevel(getLogLevel());
		}
		return logger;
	}
}
