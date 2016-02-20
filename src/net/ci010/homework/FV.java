package net.ci010.homework;

import java.util.Scanner;

import static java.lang.System.out;

import java.io.InputStream;

public class FV
{
	private static final String NAME = "Hongze Xu", EMAIL = "hoxu@ucsc.edu",
			TITLE = "FV", NUM = "1";

	public static void main(String[] args)
	{
		out.println("Name: ".concat(NAME).concat(", E-Mail: ").concat(EMAIL));
		out.println("Title: ".concat(TITLE).concat(" Number: ").concat(NUM));
		out.println();

		Collector col = new Collector(System.in);

		double result = calculate(col.get("Please enter the amount to be invested.", doubleParser),
				col.get("Please enter the annual interest rate.", doubleParser),
				col.get("Please enter the number of compounds to be made each year.", intParser),
				col.get("Please enter the number of years the money is to be invested.", intParser));
		out.printf("The future value is %.2f.", result);
	}

	public interface StringParser<T>
	{
		T parse(String value) throws IllegalArgumentException;
	}

	static StringParser<Double> doubleParser = new StringParser<Double>()
	{
		@Override
		public Double parse(String value)
		{
			try
			{
				return Double.valueOf(value);
			}
			catch (Exception e)
			{
				throw new IllegalArgumentException("Invalid type of input! The type should be double!");
			}
		}
	};

	static StringParser<Integer> intParser = new StringParser<Integer>()
	{
		@Override
		public Integer parse(String value)
		{
			try
			{
				return Integer.valueOf(value);
			}
			catch (Exception e)
			{
				throw new IllegalArgumentException("Invalid type of input! The type should be integer!");
			}
		}
	};

	public static class Collector
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

	public static double calculate(double amount, double rate, int compounds, int years)
	{
		return amount * Math.pow(1 + rate / compounds, compounds * years);
	}
}
