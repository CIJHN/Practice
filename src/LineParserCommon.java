import java.util.*;

/**
 * @author ci010
 */
public class LineParserCommon implements LineParser<State>
{
	private State last = null;

	/**
	 * Parse the lines and return the first room.
	 *
	 * @param lines The lines will be parsed.
	 * @return The first room in the text file.
	 */
	@Override
	public State parseLine(List<String> lines)
	{
		StackResize<TileOption> buff = new StackResize<>();
		for (String line : lines)
			if (this.isValid(line))
			{
				String content = line.substring(2);
				switch (line.charAt(0))
				{
					case 'r':
						State temp = new State(content);
						last = temp;
						break;
					case 'd':
						last.addDescription(content);
						break;
					case 'o':
						buff.push(new TileOption(last.getTag(), content));
						break;
					case 't':
						buff.peek().target = content;
						break;
					default:
						throw new IllegalArgumentException("The line in file has wrong format!");
				}
			}
		for (final TileOption tileOption : buff)
		{
			if (tileOption.description == null)
				throw new IllegalArgumentException("The description of the  " + tileOption.owner + " not " +
						"found! This should not happen!");
			if (tileOption.isComplete())
				if (State.map.containsKey(tileOption.target))
					try
					{
						State.map.get(tileOption.owner).addOption(new Option()
						{
							private State state = State.map.get(tileOption.target);
							private String desc = tileOption.description;

							@Override
							public String getDescription()
							{
								return desc;
							}

							@Override
							public State nextState()
							{
								return state;
							}
						});
					}
					catch (NullPointerException e)
					{
						throw new IllegalArgumentException("The tag " + tileOption.owner + " not found! This should not " +
								"happen");
					}
				else
					throw new IllegalArgumentException("Not found a room named \"" + tileOption.target + "\". This " +
							"is " +
							"a" +
							" " +
							"format error!");
			else
				throw new IllegalArgumentException("The option " + tileOption.description + " pointing to an " +
						"invalid room! This is a format error!");
		}

		State result = null;
		for (State state : State.map.values())
		{
			result = state;
			break;
		}
		this.last = null;
		return result;
	}

	/**
	 * @param line A input line.
	 * @return If the line is valid.
	 */
	public boolean isValid(String line)
	{
		if (line.charAt(1) != ' ')
			throw new IllegalArgumentException("The line in file has wrong format!");
		return !(line.isEmpty() || line.equals(""));
	}


	class TileOption
	{
		String owner;
		String description;
		String target;

		TileOption(String owner, String des)
		{
			this.owner = owner;
			this.description = des;
		}

		boolean isComplete()
		{
			return target != null;
		}
	}
}

