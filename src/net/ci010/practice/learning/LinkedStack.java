package net.ci010.practice.learning;

import java.util.Iterator;

/**
 * @author ci010
 *
 */
public class LinkedStack<Item> implements Iterable<Item>
{
	private Node first = null;

	public LinkedStack()
	{

	}

	public Item pop()
	{
		Item item = first.item;
		first = first.next;
		return item;
	}

	public void push(Item item)
	{
		Node oldNode = first;
		first = new Node();
		first.item = item;
		first.next = oldNode;
	}

	public boolean isEmptypopUsingNode()
	{
		return first == null;
	}

	/**
	 * @author ci010
	 * @classCost 16 bytes for object overhead, 8 bytes for inner class extra
	 * @referenceCost 8 bytes for String reference 8 bytes for Node reference
	 * @totalCost 40 bytes per stack node
	 * 
	 * 
	 */
	private class Node
	{
		Item item;
		Node next;
	}

	@Override
	public Iterator<Item> iterator()
	{
		return new Iterator<Item>()
		{
			private Node current = first;

			@Override
			public boolean hasNext()
			{
				return current != null;
			}

			@Override
			public Item next()
			{
				Item item = current.item;
				current = current.next;
				return item;
			}

			@Override
			public void remove()
			{
				// TODO 自动生成的方法存根

			}
		};
	}
}
