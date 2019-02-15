package structures;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class PQCompare<Type extends Comparable<Type>> {
	ArrayList<Type> list;
	Comparator<Type> comp;

	public PQCompare() {
		list = new ArrayList<>();
		comp = null;
	}

	public PQCompare(Comparator<Type> comp) {
		this();
		this.comp = comp;
	}

	public void add(Type element) {
		list.add(element);
		if (comp == null) {
			Collections.sort(list);
		} else {
			Collections.sort(list, comp);
		}
	}

	public Type remove() {
		return list.remove(0);
	}

	public Type top() {
		return list.get(0);
	}
}
