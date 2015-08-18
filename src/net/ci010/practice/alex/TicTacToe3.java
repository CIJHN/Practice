package net.ci010.practice.alex;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe3
{
	static Random r = new Random();
	static Scanner console = new Scanner(System.in);

	public static void println(String s)
	{
		System.out.println(s);
	}

	public static void init()
	{
		println("Let's play Èý×ÓÆå. Do you want to go first?");

		String yn;
		do
		{ // keep running if the user enters nonsense
			println("Please enter Y for yes, N for no.");
			yn = console.next();
		}
		while (!(yn.equals("Y") || yn.equals("N")));

		if (yn.equals("Y"))
			newGame(true);
		else if (yn.equals("N"))
			newGame(false);

		console.close();
	}

	private static void newGame(boolean side)
	{
//		BoradGame game = new BoradGame(new TicTacToe());
//		game.start(side);
	}
	
	public static String getStringInput(String message)
	{
		println(message);
		return console.nextLine();
	}
	
	public static int getIntInput(String message)
	{
		println(message);
		return console.nextInt();
	}

}
