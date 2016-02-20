/**
 * @author ci010
 */
public interface Option
{
	/**
	 * @return The description of this option.
	 */
	String getDescription();

	/**
	 * @return The state it pointing to.
	 */
	State nextState();
}
