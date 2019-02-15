package structures;

/**
 * ArrayListFactory
 */
public class ArrayListFactory implements ListFactory {
	@Override
	public <Type> List<Type> create() {
		return new ArrayList<Type>();
	}
}