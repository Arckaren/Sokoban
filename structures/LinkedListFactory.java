package structures;

/**
 * LinkedListFactory
 */
public class LinkedListFactory implements ListFactory {

	@Override
	public <Type> List<Type> create() {
		return new LinkedList<Type>();
	}
}