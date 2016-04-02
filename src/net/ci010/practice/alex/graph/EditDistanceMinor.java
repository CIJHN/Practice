package net.ci010.practice.alex.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import net.ci010.common.Session;

/**
 * @author ci010
 */
public class EditDistanceMinor
{
	private static Scanner STDIN = new Scanner(System.in);
	private static Session session = new Session();

	public static void main(String[] Args) throws FileNotFoundException
	{
//		File file = getFileFromSTDIN();
		File file = new File("C:\\Users\\CIJhn\\Desktop\\dict.txt");
		session.start("create dic");
		Dictionary dictionary = new Dictionary(new Scanner(file));
		session.end();
		while (STDIN.hasNext())
		{
			System.out.print("Please input two words: ");
			String a = STDIN.next(), b = STDIN.next();
			session.start("find path");
			List<String> path = dictionary.pathOf(a, b);
			session.end();
			System.out.println();
			if (path == null)
				System.out.println("Word does not exist");
			else if (path.isEmpty())
				System.out.println("No solution");
			else
				for (String s : path)
					System.out.println(s);
		}
	}


	static class Dictionary
	{
		private Map<String, Node> dictionary = new HashMap<>();

		public Dictionary(Iterator<String> words)
		{
			session.start("read file");
			List<Node> nodeList = newArrayList();
			while (words.hasNext())
			{
				String word = words.next();
				Node node = new Node(word);
				nodeList.add(node);
				dictionary.put(word, node);
			}
			session.end();
			session.start("connect");
			int length = nodeList.size();
			for (int index = 0; index < length; index++)
			{
				Node node = nodeList.get(index);
				char[] alphabetArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
										'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
										'z'};
				char[] chars = node.data.toCharArray();
				for (int i = 0; i < chars.length; i++)
				{
					for (int j = 0; j < alphabetArray.length; j++)
					{
						char temp = chars[i];
						chars[i] = alphabetArray[j];
						String subWord = new String(chars);
						chars[i] = temp;
						Node next;
						if ((next = dictionary.get(subWord)) != null)
						{
							int diffIdx = -1;
							for (int k = 0; k < subWord.length(); k++)
							{
								if (next.data.charAt(k) != node.data.charAt(k))
									if (diffIdx == -1)
										diffIdx = k;
									else
									{
										diffIdx = -2;
										break;
									}
							}
							if (diffIdx >= 0)
								this.connect(node, next, diffIdx);
						}
					}
				}
			}
			session.end();
		}

		private void connect(Node node, Node target, int index)
		{
			node.addNeighbor(target, index);
			target.addNeighbor(node, index);
		}

		public List<String> pathOf(String word, String target)
		{
			if (!dictionary.containsKey(word) || !dictionary.containsKey(target))
				return null;
			if (word.length() != target.length())
				return Collections.emptyList();
			return dictionary.get(word).pathTo(dictionary.get(target));
		}
	}


	static class Node
	{
		private static Comparator<Node> comparator = new Comparator<Node>()
		{
			@Override
			public int compare(Node o1, Node o2)
			{
				return o1.data.compareTo(o2.data);
			}
		};
		private TreeSet<Node>[] path;
		public final String data;
		private static List<Integer> cache = new LinkedList<>();

		public Node(String data)
		{
			this.data = data;
			this.path = new TreeSet[this.data.length()];
		}

		void addNeighbor(Node node, int diffIdx)
		{
			TreeSet<Node> neighbor;
			if ((neighbor = path[diffIdx]) != null)
				neighbor.add(node);
			else
				this.path[diffIdx] = newTreeSet(comparator, node);
		}

		public List<String> pathTo(Node node)
		{
			List<String> list = newArrayList();
			cache.clear();
			for (int i = 0; i < data.length(); i++)
				if (data.charAt(i) != node.data.charAt(i))
					cache.add(i);
			this.discover(list, node);
			cache.clear();
			return list;
		}

		private void discover(List<String> list, Node node)
		{
			int j = 0;
			for (int i : cache)
			{
				Node near = null;
				if (path[i] != null)
					for (Node n : path[i])
						if (n.data.charAt(i) == node.data.charAt(i))
							near = n;
				if (near != null)
				{
					list.add(near.data);
					cache.remove(i);
					if (j > 0)
					{
						discover(list, node);
						return;
					}
				}
				++j;
			}
		}
	}


	public static File getFileFromSTDIN()
	{
		File dictionaryFile;
		do
		{
			System.out.print("Enter name of dictionary file: ");
			String fileName = STDIN.next();
			dictionaryFile = new File(fileName);
			if (!dictionaryFile.exists())
			{
				System.out.println("File Not Found. Try again.");
				//Let the user do it again if the user entered a non-existing file name
			}
		} while (!dictionaryFile.exists());
		return dictionaryFile;
	}

	static <T> ArrayList<T> newArrayList()
	{
		return new ArrayList<>();
	}

	static <T> ArrayList<T> newArrayList(int size)
	{
		return new ArrayList<>(size);
	}

	static <T> TreeSet<T> newTreeSet(Comparator<T> comparator, T... init)
	{
		TreeSet<T> set = new TreeSet<>(comparator);
		Collections.addAll(set, init);
		return set;
	}


}
