package net.ci010.practice;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.Pattern;

import net.ci010.homework.WCount;
import net.ci010.practice.alex.BirthDay;
import net.ci010.practice.calender.Calender;
import net.ci010.practice.eric.Human;
import net.ci010.practice.foodcraft.ReflectionTest;
import net.ci010.practice.grammar.Regexp;
import net.ci010.practice.random.RandomTest;
import net.ci010.practice.reflection.GetTest;

//import net.ci010.practice.eric.Human;

public class TestRun
{
	static int var;

	public static void main(String[] args) throws FileNotFoundException
	{
//        Regexp.format3(new Scanner(new File("C:\\Users\\CIJhn\\Desktop\\test.txt")));
		//		System.out.println(var);
		//		List<String> test = new ArrayList<>();
		//		test.add("231");
		//		test.add("123451");
		//		test.add("1735");
		//		test.add("217856");
		//		test.add("172513");
		//		test.add("6123545");
		//		test.add("231");
		//		int i = 0;
		//		for (String s : test)
		//		{
		//			i++;
		//			if (s.equals("172513"))
		//			{
		//				test.remove(i);
		//				test.add("172513");
		//			}
		//			else
		//				System.out.println(s);
		//		}
		//		GetTest.test();
//		WCount.main(args);
//		Human eric = new Human("Eric", 173.5F, 60F);
//		eric.say("faq");
//		eric.introSelf();
//		System.out.println(eric.getHeight());

//		int diff = Calender.INSTANCE.getDiff(Calender.newDate(2016, 2, 18), Calender.newDate(2016, 3, 18));
//		System.out.println(diff);
		BirthDay.main(args);
	}


	static void testLog() throws FileNotFoundException
	{
		File logFile = new File("C:\\Users\\CIJhn\\Desktop\\bill\\test.txt");
		PrintStream out = System.out, instead = new PrintStream(logFile)
		{
		};
		System.setOut(instead);
	}

	private static void testRandom(int size)
	{
		float[] temp = RandomTest.generateTalent(size);
		for (int i = 0; i < size; i++)
			out.println(temp[i]);
	}

	public static void t(int count)
	{
		long i = 1, ji = 1;

		final int OUTBOUND = 12;
		int digit = String.valueOf(count).length();
		while (i <= count)
		{
			ji = ji * i;
			if (i == 12)
			{
				System.out.println(ji);
				System.out.println(String.valueOf(ji).length());
			}
			if (ji > Integer.MAX_VALUE)
			{
				System.out.println(i);
				System.out.println(ji);
				System.out.println(Integer.MAX_VALUE);
				return;
			}
			i++;
		}
		System.out.println(ji);
	}

	public static int numCarry(int multiply, int num)
	{
		String multStr = String.valueOf(multiply);
		String numStr = String.valueOf(num);
		int head = Integer.valueOf(String.valueOf(numStr.charAt(0)));
		if (String.valueOf(multiply * head).length() > 1)
		{

		}
		return 0;
	}

	public static void testT()
	{
		try
		{
			throw new Throwable();
		}
		catch (Throwable e)
		{
			System.out.println(e.getStackTrace()[0].getClassName());
		}
	}

	public static void testDouble()
	{
		Scanner sca = new Scanner(in);
		try
		{
			sca.nextDouble();
		}
		catch (InputMismatchException e)
		{
			System.out.println("please input a number");
		}
	}

	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	static void testSwitch()
	{
		//		String shape;
		//		switch(shape)
		//		{
		//			case "circle":
		//				testDouble();
		//				break;
		//			case "square":
		//				break;
		//			default:
		//				break;
		//		}
		//
		//		if(shape.equals("circle"))
		//			testDouble();
		//		else if(shape.equals("square"))
		//			return;
		//		else
		//			return;
	}
}
