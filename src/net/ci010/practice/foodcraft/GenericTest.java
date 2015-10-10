package net.ci010.practice.foodcraft;


public class GenericTest<ItemType extends Item>
{
	ItemType type;

	public void printName()
	{
		try
		{
			ItemType.get();
			type.getClass();
		}
		catch (NullPointerException e)
		{
			System.out.println(e.getStackTrace()[0].getClassName());
		}

		// System.out.println(type.getClass().getName());
	}
}
