package net.ci010.practice.reflection;

/**
 * @author ci010
 */
public class GetTest
{
	public Data data;

	public static void test()
	{
		Father f = new Father();
		GetTest test = new GetTest();
		try
		{
			test.data = (Data) f.getClass().getDeclaredField("data").get(f);
			if (test.data == null)
			{
				test.data = new Data();
				test.data.data = 100;
				f.getClass().getDeclaredField("data").set(f, test.data);
			}
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		System.out.println(f.data.data);
	}
}
