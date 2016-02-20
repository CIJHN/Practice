package net.ci010.practice.calender;

/**
 * @author ci010
 */
public enum Calender
{
	INSTANCE;

	public static Date newDate(int year, int mouth, int day)
	{
		Date date = new Date(year, mouth, day);
		if (INSTANCE.isValid(date))
			return date;
		else
			throw new IllegalArgumentException("Not a valid date!");
	}

	private static class Date
	{
		public final int year, mouth, day;
		public final boolean isLeap;

		private Date(int year, int mouth, int day)
		{
			this.year = year;
			this.mouth = mouth;
			this.day = day;
			this.isLeap = year % 4 == 0;
		}
	}

	private boolean isValid(Date date)
	{
		return date.year > 0 && date.mouth <= 12 && date.mouth > 0 && date.day > 0 && date.day <= this.getMonthLength
				(date.isLeap, date.mouth);
	}

	public int getDiff(Date origin, Date target)
	{
		int dayDiff = origin.day - target.day;
		int min = Math.min(origin.mouth, target.mouth), max = Math.max(origin.mouth, target.mouth);
		for (int i = min; i < max; i++)
			dayDiff += this.getMonthLength(target.isLeap, i);
		min = Math.min(origin.year, target.year);
		max = Math.max(origin.year, target.year);
		for (int i = min; i < max; i++)
			dayDiff += this.getYearLength(i);
		return dayDiff;
	}

	private int getYearLength(int year)
	{
		return this.isLeap(year) ? 366 : 365;
	}

	private int getMonthLength(boolean isLeap, int month)
	{
		if (month > 7)
			return month % 2 == 0 ? 31 : 30;
		else if (month == 2)
			return isLeap ? 29 : 28;
		else
			return month % 2 == 1 ? 31 : 30;
	}

	private boolean isLeap(int year)
	{
		return year % 4 == 0;
	}
}
