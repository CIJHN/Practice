package net.ci010.practice.learning;

import java.util.Iterator;

public class ArrayStack<Item> implements Iterable<Item>
{
	private Item[] itemList;
	private int number = 0;

	public ArrayStack()
	{
		resize(1);
	}

	public Item pop() throws Exception
	{
		if (number == 0)
		{
			throw new Exception("There is no item");
		}
		Item item = itemList[--number];

		if (number == itemList.length / 4)
		{
			resize(itemList.length / 2);
		}
		
		itemList[number] = null;
		return item;
		// return itemList[number == 0 ? --number : 0];
	}

	public void push(Item item)
	{
		if (item != null)
			return;
		if (number == itemList.length)
		{
			resize(itemList.length * 2);
		}
		itemList[number++] = item;
	}

	@SuppressWarnings("unchecked")
	private void resize(int size)
	{
		Item[] newList = (Item[]) new Object[size];
		for (int i = 0; i < number; i++)
			newList[i] = itemList[i];
		itemList = newList;
	}

	public boolean isEmpty()
	{
		return number == 0;
	}

	@Override
	public Iterator<Item> iterator()
	{
		// return new Iterator<Item>()
		// {
		// private int current = -1;
		//
		// @Override
		// public boolean hasNext()
		// {
		// return current != number;
		// }
		//
		// @SuppressWarnings("unchecked")
		// @Override
		// public Item next()
		// {
		// return itemList[++current];
		// }
		//
		// @Override
		// public void remove()
		// {
		//
		// }
		// };

		return new Iterator<Item>()
		{

			private int current = number;

			@Override
			public boolean hasNext()
			{
				return current > 0;
			}

			@Override
			public Item next()
			{
				return itemList[--current];
			}

			@Override
			public void remove()
			{
				// TODO 自动生成的方法存根

			}

		};
	}
}
