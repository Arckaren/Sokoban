package structures;

import java.util.Collections;
import java.util.ArrayList;

public class PQ<Type> {
	ArrayList<Tuple<Type, Integer>> list;

	public PQ() {
		list = new ArrayList<>();
	}

	public void add(Type element, Integer priority) {
		Tuple<Type, Integer> tuple = new Tuple<>(element, priority);
		list.add(tuple);
		Collections.sort(list, (a, b) -> {
			return a.getY().compareTo(b.getY());
		});
	}

	public Type remove() {
		return list.remove(0).getX();
	}

	public Type top() {
		return list.get(0).getX();
	}
}
