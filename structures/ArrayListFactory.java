package structures;

/**
 * ArrayListFactory
 */
public class ArrayListFactory implements ListFactory {
	@Override
	public List create() {
		return new ArrayList();
	}
}