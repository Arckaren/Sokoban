package structures;

import java.util.Iterator;

/**
 * LinkedListIterator
 */
public class ArrayListIterator implements Iterator<Integer> {
	ArrayList al;
	int currPos;

	protected ArrayListIterator(ArrayList list) {
		al = list;
		currPos = -1;
	}

	@Override
	public boolean hasNext() {
		return (al.getSize() - 1 > currPos);
	}

	@Override
	public Integer next() {
		currPos++;
		return al.get(currPos);
	}

	@Override
	public void remove() {
		al.remove(currPos);
		currPos--;
	}

}