package structures;

/**
 * ListFactory
 */
public interface ListFactory {
	<Type> List<Type> create();
}