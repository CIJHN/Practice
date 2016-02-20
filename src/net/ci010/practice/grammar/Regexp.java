package net.ci010.practice.grammar;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * @author ci010
 */
public class Regexp
{
	public static void format(Scanner scanner)
	{
		for (int i = 1; scanner.hasNextLine(); ++i)
		{
			String line = scanner.nextLine();
			System.out.printf("line %3d: [%s]%n", i, line);

			List<String> words = new LinkedList<>();

			for (String word : line.split("\\s+"))
			{
				if (word.length() == 0)
					continue;
				System.out.printf("...[%s]%n", word);
				words.add(word);
			}
			System.out.printf("list:");

			for (String word : words)
			{
				System.out.printf(" %s", word);
			}
			System.out.printf("%n");
		}
	}

	public static void format3(Scanner scanner)
	{
		List<String> words = new LinkedList<>();

		for (int i = 1; scanner.hasNextLine(); ++i)
		{
			String line = scanner.nextLine();
			StringBuffer buffer = new StringBuffer();
			for (String word : line.split("\\s+"))
			{
				if (word.length() == 0)
					continue;
				buffer.append(word).append(" ");
			}
			words.add(buffer.toString());
		}
		for (String word : words)
		{
			System.out.println(word);
		}
	}

	public static void format2(Scanner scanner)
	{
		for (int i = 1; scanner.hasNextLine(); ++i)
		{
			String line = scanner.nextLine();
			List<String> words = new LinkedList<>();
			for (String word : line.split("\\s+"))
			{
				if (word.length() == 0)
					continue;
				words.add(word);
			}
			print_paragraph(words);
		}
	}

	private static void print_paragraph(List<String> words)
	{
		ListIterator<String> listIterator = words.listIterator();
		while (listIterator.hasNext())
		{
			System.out.print(listIterator.next().concat(" "));
			listIterator.remove();
		}
	}
}
