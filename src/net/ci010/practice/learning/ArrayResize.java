package net.ci010.practice.learning;

public class ArrayResize<Item>
{
	protected Item[] itemList;
	protected int number = 0;

	@SuppressWarnings("unchecked")
	protected void resize(int size)
	{
		Item[] newList = (Item[]) new Object[size];
		for (int i = 0; i < number; i++)
			newList[i] = itemList[i];
		itemList = newList;
	}

	protected void insert(int index, Item item)
	{
		for (int i = number++; i > index; i--)
			itemList[i] = itemList[i - 1];
		if (number == itemList.length)
			resize(itemList.length * 2);
	}

	protected void remove(int index, Item item)
	{
		itemList[index] = null;
		for (int i = index; i < number - 1; i++)
			itemList[i] = itemList[i + 1];
		if (number == itemList.length / 4)
			resize(itemList.length / 2);
	}
}
