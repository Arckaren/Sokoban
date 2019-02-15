
import java.util.ArrayList;
import java.util.Iterator;

import global.*;
import structures.List;
import structures.ListFactory;

/**
 * Game
 */
public class Game {
	public static void main(String[] args) {
		Configuration conf = Configuration.getInstance();
		LevelReader lvReader = new LevelReader(ClassLoader.getSystemClassLoader().getResourceAsStream("Original.txt"));
		ArrayList<Level> lvls = new ArrayList<>();
		Level lvl;
		while ((lvl = lvReader.readNextLevel()) != null) {
			lvls.add(lvl);
		}
		conf.logger().info("nb levels read: " + lvls.size());
		LevelPrinter lvlPrinter = new LevelPrinter(System.out);
		lvlPrinter.print(lvls.get(0));

		ListFactory lF = conf.getListFactory();
		List<Integer> l = lF.create();

		l.addHead(4);
		int elem = l.popHead();
		conf.logger().info("Elem: " + elem);

		l.addTail(0);
		l.addTail(1);
		l.addTail(1);
		l.addTail(0);
		l.addTail(0);

		System.out.println(l);
		Iterator<Integer> i = l.iterator();
		while (i.hasNext()) {
			if (i.next() == 0) {
				i.remove();
			}
		}

		System.out.println(l);
	}
}