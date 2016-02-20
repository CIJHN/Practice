package net.ci010.homework.cmps12b;

/**
 * @author ci010
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

class Queue<Item> implements Iterable<Item>
{
	private class Node
	{
		Item item;
		Node next;

		public Node(Item item, Node next)
		{
			this.item = item;
			this.next = next;
		}
	}

	private Node head = null;
	private Node tail = null;

	public boolean isEmpty()
	{
		return this.head == null & this.tail == null;
	}

	public void insert(Item newitem)
	{
		if (this.isEmpty())
			this.head = this.tail = new Node(newitem, null);
		else
			this.tail = this.tail.next = new Node(newitem, null);
	}

	public Iterator<Item> iterator()
	{
		return new Itor();
	}

	class Itor implements Iterator<Item>
	{
		Node current = head;

		public boolean hasNext()
		{
			return current != null;
		}

		public Item next()
		{
			if (!hasNext()) throw new NoSuchElementException();
			Item result = current.item;
			current = current.next;
			return result;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

}
