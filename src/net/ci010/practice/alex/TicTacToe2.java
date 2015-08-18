package net.ci010.practice.alex;

import java.util.*;

public class TicTacToe2
{
	static Situation situation;
	static String[][] tic = new String[3][3];
	static Scanner console = new Scanner(System.in);
	static Random rand = new Random();
	static int rounds = 0;

	public static void go()
	{
		for (int j = 0; j <= 2; j++)
		{
			for (int k = 0; k <= 2; k++)
			{
				tic[j][k] = "N";
			}
		}

		System.out.println("Let's play 三子棋. Do you want to go first?");
		String yn = "o";
		do
		{ // keep running if the user enters nonsense
			System.out.println("Please enter Y for yes, N for no.");
			yn = console.next();
		}
		while (!yn.equals("Y") && !yn.equals("N"));

		if (yn.equals("Y"))
		{
			playone(); // user go first
		}
		else if (yn.equals("N"))
		{ // computer go first
			tic[0][0] = "O";
			playone();
		}

		console.close();
	}

	enum Situation
	{
		danger, nothing, win, lose;

		int x, y;

		static Situation forY(int x, int y)
		{
			Situation temp = Situation.danger;
			danger.x = x;
			danger.y = y;
			return temp;
		}

	}

	static void println(String s)
	{
		System.out.println(s);
	}

	static void playerMove()
	{
		int r = 0; // user row
		int c = 0; // user colume

		do
		{
			do
			{
				System.out.print("Enter row(1 to 3): ");
				r = console.nextInt() - 1;
			}
			while (r > 3);
			do
			{
				System.out.print("Enter column(1 to 3): ");
				c = console.nextInt() - 1;
			}
			while (c > 3);
		}
		while (!tic[r][c].equals("N")); // if the
										// array
										// place has
										// been
										// occupied,
										// input
										// again
		tic[r][c] = "X"; // user puts an X in array
	}

	public static void playone()
	{
		situation = Situation.nothing;

		println("playone");

		while (situation == Situation.nothing || situation == Situation.danger)
		{ // keep running if nobody wins
			rounds++; // round +1 in each loop
			
			playerMove();

			paintResult();

			computerMove(); // determine win or lose or danger

			paintResult();

			System.out.println("Round " + rounds + " end.");

		} // play time

	}

	// 判定胜负
	public static void computerMove()
	{ // determine situations
		String row = ""; // the sum of strings
		String col = "";
		println("computer move");

		situation = Situation.nothing;

		for (int j = 0; j <= 2; j++)
		{
			row = "";
			col = "";
			for (int k = 0; k <= 2; k++)
			{
				col += tic[j][k]; // add all in a column
				row += tic[k][j];// add all in a row
			}

			minidetermine(row);

			minidetermine(col);
		}

		String sum = "";

		for (int j = 0; j <= 2; j++)
			sum = sum + tic[j][j]; // add the diagonal

		minidetermine(sum);

		sum = "";
		for (int j = 0; j <= 2; j++)
			sum += tic[j][2 - j]; // add the diagonal

		minidetermine(sum); // determine

		println("round is " + situation);

		if (situation == Situation.nothing)
		{
			println("do normal rand");
			int i, j;
			do
			{ // random paly
				i = rand.nextInt(3); // from 0 to 2
				j = rand.nextInt(3); // from 0 to 2
			}
			while (!tic[i][j].equals("N"));// if
											// the
											// array
											// place
											// has
											// been
											// occupied,
											// random
											// again
			tic[i][j] = "O"; // user puts an O in array
		}
		else
			// block();
			block();

	}

	static boolean patchR(int num)
	{
		for (int t = 0; t <= 2; t++)
		{
			if (tic[num][t].equals("N"))
			{
				println("find place in " + num + " " + t);
				tic[num][t] = "O"; // make move
				return true;
			}
		}
		return false;
	}

	static boolean patchC(int num)
	{
		for (int t = 0; t <= 2; t++)
		{
			if (tic[t][num].equals("N"))
			{
				println("find place in " + num + " " + t);
				tic[t][num] = "O"; // make move
				return true;
			}
		}
		return false;
	}

	static boolean willWin(String sum)
	{
		return sum.equals("OON") || sum.equals("NOO") || sum.equals("ONO");
	}

	static boolean willLose(String sum)
	{
		return sum.equals("NXX") || sum.equals("XXN") || sum.equals("XNX");
	}

	static String sumR(int r)
	{
		String s = "";
		for (int i = 0; i < 3; i++)
			s += tic[r][i];
		return s;
	}

	static String sumC(int c)
	{
		String s = "";
		for (int i = 0; i < 3; i++)
			s += tic[i][c];
		return s;
	}

	static void block()
	{
		String sum; // the sum of strings

		println("block");

		for (int j = 0; j <= 2; j++)
		{
			sum = "";
			println("start -");
			for (int k = 0; k <= 2; k++)
				sum += tic[j][k];

			println(sum);

			if (willWin(sum))
				if (patchR(j))
				{
					minidetermine(sumR(j));
					return;
				}
				else
					println("fatal error on - " + j);

			if (willLose(sum))
				if (patchR(j))
					return;
				else
					println("fatal error on -" + j);

			println("start |");
			sum = "";

			for (int k = 0; k <= 2; k++)
				sum += tic[k][j];

			if (willWin(sum))
				if (patchC(j))
				{
					minidetermine(sumC(j));
					return;
				}
				else
					println("fatal error on |" + j);

			if (willLose(sum))
				if (patchC(j))
					return;
				else
					println("fatal error on |" + j);
		}

		sum = "";
		for (int v = 0; v <= 2; v++)
		{ // add or block 2 in a row in diagonal form right to left
			sum += tic[v][v];
		}

		if (willWin(sum))
			for (int g = 0; g <= 2; g++)
				if (tic[g][g].equals("N"))
				{
					tic[g][g] = "O"; // make move
					sum = "";
					for (int v = 0; v <= 2; v++)
						sum += tic[v][v];

					minidetermine(sum);
					return;
				}
		// else
		// println("fatal error on \\ willWin di" + g);

		if (willLose(sum))
			for (int g = 0; g <= 2; g++)
				if (tic[g][g].equals("N"))
				{
					tic[g][g] = "O"; // make move
					return;
				}
		// else
		// println("fatal error on \\ willLose di" + g);

		sum = "";
		for (int e = 0; e <= 2; e++)
			sum += tic[e][2 - e]; // make move

		if (willWin(sum))
			for (int g = 0; g <= 2; g++)
				if (tic[g][2 - g].equals("N"))
				{
					tic[g][2 - g] = "O"; // make move

					sum = "";
					for (int e = 0; e <= 2; e++)
						sum += tic[e][2 - e]; // make move
					minidetermine(sum);

					return;
				}
		// else
		// println("fatal error on / di" + g);

		if (willLose(sum))
			for (int g = 0; g <= 2; g++)
				if (tic[g][2 - g].equals("N"))
				{
					tic[g][2 - g] = "O"; // make move
					return;
				}
		// else
		// println("fatal error on / di" + g);
	}


	public static void minidetermine(String sum)
	{ // for determine situation of the string sum

		if (situation == Situation.danger)
			return;

		if (sum.equals("XXX"))
		{
			situation = Situation.win;
			end();
		}
		else if (sum.equals("OOO"))
		{
			situation = Situation.lose;
			end();
		}
		else if (sum.equals("NOO") || sum.equals("OON") || sum.equals("NXX") || sum.equals("XXN") || sum.equals("ONO") || sum.equals("XNX"))
		{
			situation = Situation.danger;
			println("now is " + situation);
		}
		else
		{
			situation = Situation.nothing;
			println("now is " + situation);
		}

	}

	static void end()
	{
		System.out.println("Round " + rounds + " end.");
		System.out.println("You " + situation + ".");
		System.exit(-1);
	}

	public static void paintResult()
	{
		// 打出结果，此可分开成单独的
		for (int i = 0; i <= 2; i++)
		{
			System.out.print("| ");

			for (int k = 0; k <= 2; k++)
			{
				if (tic[i][k].equals("N"))
				{
					System.out.print("  ");
				}
				else
				{
					System.out.print(tic[i][k] + " ");
				}
			}
			System.out.print("|");
			System.out.println("");
		}
		System.out.println("+-------+");
		System.out.println("");// print out the current plays
	}
}
