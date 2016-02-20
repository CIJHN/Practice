package net.ci010.practice.alex;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author ci010
 */
public class BirthDay
{
	public static void main(String[] args) throws FileNotFoundException
	{
		int Rowinput[] = new int[3];
		Rowinput = TakeInUserInp();
		PrintOutput(Rowinput);
	}


	public static int[] TakeInUserInp()
	{
		int Rawinput[] = new int[3];
		System.out.print("What month, day, and year were you born? ");
		Scanner inp = new Scanner(System.in);
		for (int i = 0; i < 3; i++)
		{
			Rawinput[i] = inp.nextInt();
		}
		return Rawinput;
	}


	public static void PrintOutput(int Rowinput[])
	{
		int month = Rowinput[0];
		int day = Rowinput[1];
		int year = Rowinput[2];
		TeacherDate bday = new TeacherDate(year, month, day);
		TeacherDate today = new TeacherDate();
		//System.out.println(today.toString());
		//System.out.println(bday.toString());
		System.out.println("You were born on " + bday.toString() + ", which was a " + bday.getDayOfWeek());
		if (bday.isLeapYear())
		{
			System.out.println(year + " was a leap year");
		}
		ToBirthDay(bday, today);

		int dayold = DayOld(bday, today);
		System.out.println("You are " + dayold + " days old.");
	}


	public static void ToBirthDay(TeacherDate bday, TeacherDate today)
	{

		if (bday.getMonth() == today.getMonth() && bday.getDay() == today.getDay())
		{
			System.out.println("Happy birthday! You are now age " + (today.getYear() - bday.getYear()) + ".");
		}
		else
		{

			int tobday = 0;
			while (!bday.equals(today))
			{
				bday.nextDay();
				tobday++;
			}
			System.out.print("It will be your birthday in " + tobday + " days.");
			System.out.print("k");
		}


	}


	public static int DayOld(TeacherDate bday, TeacherDate today)
	{
		int dayold = 0;
		while (!bday.equals(today))
		{
			bday.nextDay();
			dayold++;
		}
		return dayold;


	}


}
