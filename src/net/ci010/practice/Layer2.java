package net.ci010.practice;

public class Layer2
{
	public static void testT()
	{
		try
		{
			throw new Throwable();
		}
		catch (Throwable e)
		{
			System.out.println(e.getStackTrace()[1].getClassName());
		}
	}
}
