import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The core class of the AVGEngine
 *
 * @author ci010
 */
public class AVGEngine
{
	/**
	 * Globe separator
	 */
	public static String separator = System.getProperty("line.separator");
	static Scanner input;

	/**
	 * Initialize the engine, by parsing a script file.
	 *
	 * @param filename The name of the file containing scripts of the game.
	 */
	public static void init(String filename)
	{
		File script = new File(filename);
		try
		{
			input = new Scanner(script);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File named ".concat(filename).concat(" not found!"));
		}
		List<String> lines = new ArrayList<>();

		while (input.hasNext())
		{
			String line = input.nextLine();
			if (line.isEmpty() || line.equals(""))
				continue;
			if (line.charAt(1) != ' ')
				throw new IllegalArgumentException("The line in file has wrong format!");
			lines.add(line);
		}

		LineParser<State> parser = new LineParserCommon();
		GameData.INSTANCE.transfer(parser.parseLine(lines));
		input = new Scanner(System.in);
	}

	/**
	 * The main game loop.
	 */
	public static void loop()
	{
		while (true)
		{
			State current = GameData.INSTANCE.getCurrentState();
			if (current == null)
			{
				throw new IllegalStateException("The current state is null!");
			}
			System.out.print(current.toString());
			System.out.println(current.getOptionDescriptions());
			try
			{
				String next = input.next();
				switch (next)
				{
					case "z":
						GameData.INSTANCE.backPort();
						continue;
					case "r":
						GameData.INSTANCE.restart();
						continue;
					case "q":
						return;
					case "y":
						State st = GameData.INSTANCE.getCurrentState();
						System.out.println(st.getTag()+":");
				}
				Option opt = current.getOption(next);
				System.out.println("[".concat(opt.getDescription()).concat("]").concat(separator));
				GameData.INSTANCE.transfer(opt.nextState());
			}
			catch (IllegalArgumentException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
