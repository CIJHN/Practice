package net.ci010.practice.foodcraft;

import java.lang.reflect.Field;

public class ReflectionTest
{
	@Test
	public static int a, b, c, d, e, f, g;

	public static void test()
	{
		int i = 0;
		for (Field f : ReflectionTest.class.getFields())
		{
			try
			{
				if (f.get(null).equals(b))
				{
					System.out.println("a=b");
				}
			}
			catch (IllegalArgumentException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		for (Field f : ReflectionTest.class.getFields())
		{
			if (f.isAnnotationPresent(Test.class))
			{
				System.out.println(f.getName());

				try
				{
					f.set(null, i++);
				}
				catch (IllegalArgumentException | IllegalAccessException e)
				{
					e.printStackTrace();
				}

			}
		}
		for (Field f : ReflectionTest.class.getFields())
		{
			try
			{
				System.out.println(f.getName() + "=" + f.get(null));
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
