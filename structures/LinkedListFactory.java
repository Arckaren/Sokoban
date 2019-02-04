package structures;

/**
 * LinkedListFactory
 */
public class LinkedListFactory implements ListFactory {

	@Override
	public List create() {
		return new LinkedList();
	}
}