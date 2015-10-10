package net.ci010.practice;


import static java.lang.System.in;
import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import net.ci010.practice.foodcraft.ReflectionTest;
import net.ci010.practice.random.RandomTest;

//import net.ci010.practice.eric.Human;

public class TestRun
{
	public static void main(String[] args)
	{
		// FileSecurity.instance.decode("F:/F entry/elocollection/SecretBase/");
		// testRandom(5);
		// Father.test();
		// Son.test();
		// Layer2.testT();
		// GenericTest<Item> t = new GenericTest<Item>();
		// t.printName();
		ReflectionTest.test();
		// out.println(5*0.1);
		// TicTacToe2.go();
	}
	//
	// private static void startCoder()
	// {
	// new JFrame();
	// }

	private static void testRandom(int size)
	{
		float[] temp = RandomTest.generateTalent(size);
		for (int i = 0; i < size; i++)
			out.println(temp[i]);
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
