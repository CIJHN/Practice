package net.ci010.homework.cmps12b;

/**
 * @author ci010
 */

import java.io.*;
import java.util.Scanner;

import static java.lang.System.*;

class xref
{
	static void processFile(String filename, boolean debug) throws IOException
	{
		Scanner scan = new Scanner(new File(filename));
		Tree tree = new Tree();
		for (int linenr = 1; scan.hasNextLine(); ++linenr)
			for (String word : scan.nextLine().split("\\W+"))
			{
				out.printf("%s: %d: %s%n", filename, linenr, word);
				tree.insert(word, linenr);
			}
		scan.close();
		if (debug)
			tree.debug();
		else
			tree.output();
	}

	public static void main(String[] args)
	{
		String filename = args[0];
		boolean debug = Boolean.valueOf(args[1]);
		try
		{
			processFile(filename, debug);
		}
		catch (IOException error)
		{
			auxlib.warn(error.getMessage());
		}
		auxlib.exit();
	}
}


