package structures;

import java.util.Iterator;

/**
 * LinkedListIterator
 */
public class ArrayListIterator<Type> implements Iterator<Type> {
	ArrayList<Type> al;
	int currPos;

	protected ArrayListIterator(ArrayList<Type> list) {
		al = list;
		currPos = -1;
	}

	@Override
	public boolean hasNext() {
		return (al.getSize() - 1 > currPos);
	}

	@Override
	public Type next() {
		currPos++;
		return al.get(currPos);
	}

	@Override
	public void remove() {
		al.remove(currPos);
		currPos--;
	}

}