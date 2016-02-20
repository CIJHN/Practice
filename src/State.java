import java.util.*;

/**
 * This class represent a state of the game.
 *
 * @author ci010
 */
public class State
{
	public static final Map<String, State> map = new LinkedHashMap<>();

	private Map<Character, Option> optionMap;
	private char next = 'a';
	private String tag;
	private List<String> description;

	/**
	 * @return The tag of the state.
	 */
	public String getTag()
	{
		return tag;
	}

	/**
	 * The tag should be unique since it's the only way to distinguish a state.
	 *
	 * @param tag The tag of the state.
	 */
	public State(String tag)
	{
		this.tag = tag;
		if (map.containsKey(tag))
			throw new IllegalArgumentException("The tag of the room should be unique!");
		map.put(tag, this);
	}

	/**
	 * @param des The description will be added to this state.
	 */
	public void addDescription(String des)
	{
		if (this.description == null)
			this.description = new ArrayList<>();
		this.description.add(des);
	}

	/**
	 * @param option The option will be added to this state.
	 */
	public void addOption(Option option)
	{
		if (optionMap == null)
			optionMap = new LinkedHashMap<>();
		optionMap.put(next++, option);
	}

	/**
	 * @return All the descriptions of this state.
	 */
	@Override
	public String toString()
	{
		String result = "";
		for (String s : description)
			result = result.concat(s).concat(AVGEngine.separator).concat(AVGEngine.separator);
		return result;
	}

	/**
	 * @return All the descriptions of all the options.
	 */
	public String getOptionDescriptions()
	{
		String result = "";
		if (this.optionMap != null)
			for (Map.Entry<Character, Option> entry : optionMap.entrySet())
				result = result.concat(entry.getKey() + " - " + entry.getValue().getDescription() + AVGEngine.separator);
		return result;
	}

	/**
	 * @return All the destinations of the state.
	 */
	public String getAllDestination()
	{
		String result = "";
		if (this.optionMap != null)
			for (Option option : optionMap.values())
				result.concat(option.nextState().getTag() + " ");
		return result;
	}

	/**
	 * @param input User's choice.
	 * @return If the input string is a valid input.
	 */
	private boolean isValid(String input)
	{
		return input.length() == 1 && input.charAt(0) >= 'a' && input.charAt(0) < next;
	}

	/**
	 * @param input User's input.
	 * @return The option relate to the input string.
	 */
	public Option getOption(String input)
	{
		if (isValid(input))
			return optionMap.get(input.charAt(0));
		else
			throw new IllegalArgumentException("no such option!");
	}
}
