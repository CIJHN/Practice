package net.ci010.practice.reflection;

public class Father
{
	public static void test()
	{
		String clazzName3 = new Object()
		{
			public String getClassName()
			{
				String clazzName = this.getClass().getName();
				return clazzName.substring(0, clazzName.lastIndexOf('$'));
			}
		}.getClassName();
		System.out.println(clazzName3);
	}
}
