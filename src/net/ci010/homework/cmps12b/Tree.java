package net.ci010.homework.cmps12b;

/**
 * @author ci010
 */

import static java.lang.System.*;

class Tree
{
	private class Node
	{
		String key;
		Queue<Integer> value;
		Node left;
		Node right;

		public Node(String key, int init)
		{
			this.key = key;
			this.value = new Queue<>();
			this.value.insert(init);
		}
	}

	private Node root;

	private void debugHelper(Node tree, int depth)
	{
		if (tree.left != null)
			debugHelper(tree.left, depth + 1);
		for (int i = 0; i < depth; i++)
			out.print("  ");
		out.print(depth + " ");
		out.println(tree.key);
		if (tree.right != null)
			debugHelper(tree.right, depth + 1);
	}

	private void outputHelper(Node node)
	{
		if (node.right != null)
			this.outputHelper(node.right);
		out.print(node.key.concat(": "));
		for (Integer integer : node.value)
			out.print(integer + " ");
		out.println();
		if (node.left != null)
			this.outputHelper(node.left);
	}

	public void insert(String key, Integer linenum)
	{
		if (this.root == null)
		{
			this.root = new Node(key, linenum);
			return;
		}
		this.insert(this.root, key, linenum);
	}

	private void insert(Node current, String key, Integer line)
	{
		int result = current.key.compareTo(key);
		if (result == 0)
			current.value.insert(line);
		else if (result > 0)
			if (current.right != null)
				this.insert(current.right, key, line);
			else
				current.right = new Node(key, line);
		else if (current.left != null)
			this.insert(current.left, key, line);
		else
			current.left = new Node(key, line);
	}

	public void debug()
	{
		debugHelper(root, 0);
	}

	public void output()
	{
		this.outputHelper(this.root);
	}
}
