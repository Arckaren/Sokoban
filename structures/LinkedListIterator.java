package structures;

import java.util.Iterator;

/**
 * LinkedListIterator
 */
public class LinkedListIterator<Type> implements Iterator<Type> {
	Cell<Type> prec;
	Cell<Type> curr;
	Cell<Type> nxt;
	LinkedList<Type> ll;

	protected LinkedListIterator(LinkedList<Type> list) {
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
	public Type next() {
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