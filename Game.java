
import java.util.ArrayList;

import global.*;
import structures.List;
import structures.ListFactory;

/**
 * Game
 */
public class Game {
	public static void main(String[] args) {
		Configuration conf = Configuration.getInstance();
		ListFactory lF = conf.getListFactory();
		List l = lF.create();

		l.addHead(4);
		int elem = l.popHead();
		conf.logger().info("Elem: " + elem);
		LevelReader lvReader = new LevelReader(ClassLoader.getSystemClassLoader().getResourceAsStream("Original.txt"));
		ArrayList<Level> lvls = new ArrayList<>();
		Level lvl;
		while ((lvl = lvReader.readNextLevel()) != null) {
			lvls.add(lvl);
		}
		conf.logger().info("nb levels read: " + lvls.size());
		LevelPrinter lvlPrinter = new LevelPrinter(System.out);
		lvlPrinter.print(lvls.get(0));
	}
}