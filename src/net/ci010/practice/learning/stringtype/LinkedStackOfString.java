package net.ci010.practice.learning.stringtype;

/**
 * @author ci010
 *
 */
public class LinkedStackOfString
{
	private Node first = null;

	public LinkedStackOfString()
	{

	}

	public String pop()
	{
		String item = first.item;
		first = first.next;
		return item;
	}

	public void push(String item)
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
		String item;
		Node next;
	}
}
