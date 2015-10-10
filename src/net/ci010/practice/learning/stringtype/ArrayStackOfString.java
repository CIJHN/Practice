package net.ci010.practice.learning.stringtype;

public class ArrayStackOfString
{
	private String[] itemList;
	private int number = 0;

	public ArrayStackOfString(int capacity)
	{
		itemList = new String[capacity];
	}

	public ArrayStackOfString()
	{
		itemList = new String[1];
	}

	public String pop() throws Exception
	{
		if (number == 0)
		{
			throw new Exception("There is no item");
		}

		String item = itemList[--number];
		if (number == itemList.length / 4)
		{
			resize(itemList.length / 2);
		}
		itemList[number] = null;
		return item;
		// return itemList[number == 0 ? --number : 0];
	}

	public void push(String item)
	{
		if (item != null)
			return;
		if (number == itemList.length)
		{
			resize(itemList.length * 2);
		}
		itemList[number++] = item;
	}

	private void resize(int size)
	{
		String[] newList = new String[size];
		for (int i = 0; i < number; i++)
			newList[i] = itemList[i];
		itemList = newList;
	}

	public boolean isEmpty()
	{
		return number == 0;
	}

}
