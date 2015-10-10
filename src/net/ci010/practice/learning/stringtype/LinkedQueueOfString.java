package net.ci010.practice.learning.stringtype;

public class LinkedQueueOfString
{
	private Node first, last;

	/**
	 * @author ci010
	 * @classCost 16 bytes for object overhead, 8 bytes for inner class extra
	 * @referenceCost 8 bytes for String reference 8 bytes for Node reference
	 * @totalCost 40 bytes per stack node
	 * 
	 */
	private class Node
	{
		String item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public void enqueue(String item)
	{
		Node oldNode = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldNode.next = last;
	}

	public String dequeue()
	{
		String item = first.item;
		first = first.next;
		if (isEmpty())
			last = null;
		return item;
	}
}
