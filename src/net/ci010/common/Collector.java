package net.ci010.common;

import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * @author ci010
 */
public class Collector
{
	private Scanner scan;
	private boolean useToken;

	public Collector(InputStream input)
	{
		this.scan = new Scanner(input);
		this.useToken = false;
	}

	public Collector useToken()
	{
		this.useToken = true;
		return this;
	}

	public Collector useLine()
	{
		this.useToken = false;
		return this;
	}

	public <T> T get(String msg, StringParser<T> handler)
	{
		T value = null;
		while (value == null)
		{
			String input = this.getString(msg);
			try
			{
				value = handler.parse(input);
			}
			catch (IllegalArgumentException e)
			{
				out.println(e.getMessage());
			}
		}
		return value;
	}

	public String getString(String msg)
	{
		out.println(msg);
		String input = null;
		if (!useToken)
			input = scan.next();
		else
			input = scan.nextLine();
		return input;
	}
}
