package structures;

import java.util.Iterator;

/**
 * LinkedListIterator
 */
public class LinkedListIterator implements Iterator<Integer> {
	Cell prec;
	Cell curr;
	Cell nxt;
	LinkedList ll;

	protected LinkedListIterator(LinkedList list) {
		prec = null;
		curr = null;
		nxt = list.getHead();
		ll = list;
	}

	@Override
	public boolean hasNext() {
		return (nxt != null);
	}

	@Override
	public Integer next() {
		prec = curr;
		curr = nxt;
		nxt = nxt.getNext();
		return curr.getData();
	}

	@Override
	public void remove() {
		if (prec == null) {
			ll.setHead(nxt);
		} else {
			prec.setNext(nxt);
		}
		curr = prec;
	}

}