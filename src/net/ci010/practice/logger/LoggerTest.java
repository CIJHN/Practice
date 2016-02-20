package net.ci010.practice.logger;

import java.io.*;

/**
 * @author ci010
 */
public class LoggerTest
{
	static void test() throws FileNotFoundException
	{
		File logFile = new File("C:\\Users\\CIJhn\\Desktop\\bill\\test.txt");
		PrintStream out = System.out, instead = new PrintStream(logFile);
		System.setOut(instead);

	}

	private LoggerTest() throws FileNotFoundException
	{

	}

	private static LoggerTest instance;

	public static LoggerTest getInstance()
	{
		if (instance == null)
			try {
				instance = new LoggerTest();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return instance;
	}

}
