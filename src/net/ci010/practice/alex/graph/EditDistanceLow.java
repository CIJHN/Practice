package net.ci010.practice.alex.graph;

import java.util.*;

/**
 * @author ci010
 */
public class EditDistanceLow
{
	private static Map<String, Object[]> dict = new HashMap<>();
	private static List<Integer> cache = new LinkedList<>();

	public void init(Iterator<String> words)
	{
		List<Object[]> nodeList = new ArrayList<>();
		while (words.hasNext())
		{
			String word = words.next();
			Object[] node = newNode(word);
			nodeList.add(node);
			dict.put(word, node);
		}
		int length = nodeList.size();
		for (int index = 0; index < length; index++)
		{
			Object[] node = nodeList.get(index);
			char[] alphabetArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
									'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
									'z'};
			char[] chars = getValue(node).toCharArray();
			for (int i = 0; i < chars.length; i++)
			{
				for (int j = 0; j < alphabetArray.length; j++)
				{
					char temp = chars[i];
					chars[i] = alphabetArray[j];
					String subWord = new String(chars);
					chars[i] = temp;
					Object[] next;
					if ((next = dict.get(subWord)) != null)
					{
						int diffIdx = -1;
						for (int k = 0; k < subWord.length(); k++)
						{
							if (getValue(next).charAt(k) != getValue(node).charAt(k))
								if (diffIdx == -1)
									diffIdx = k;
								else
								{
									diffIdx = -2;
									break;
								}
						}
						if (diffIdx >= 0)
							addNeighbor(node, next, diffIdx);
					}
				}
			}
		}
	}

	public static void put(String s)
	{
		int length = s.length();
		dict.put(s, new Object[]{s, new Object[length]});
	}

	public static Object[] get(String key)
	{
		return dict.get(key);
	}

	public static String getValue(Object[] node)
	{
		return (String) node[0];
	}

	static void setNeighbor(Object[] node, int id, Object[] neighbor)
	{
		getNeighbors(node)[id].add(neighbor);
	}

	static TreeSet<Object[]>[] getNeighbors(Object[] node)
	{
		return (TreeSet<Object[]>[]) node[1];
	}

	static TreeSet<Object[]> getNeighborAt(Object[] node, int id)
	{
		return getNeighbors(node)[id];
	}


	private static void addNeighbor(Object[] node, Object[] next, int i)
	{
		TreeSet<Object[]>[] neighbors = getNeighbors(node);
		TreeSet<Object[]> n = neighbors[i];
		if (n == null)
			n = neighbors[i] = new TreeSet<>();
		n.add(next);
	}

	static Object[] newNode(String s)
	{
		return new Object[]{s, new Object[s.length()]};
	}

	public static List<String> findPath(String a, String b)
	{
		if (!dict.containsKey(a) || !dict.containsKey(b))
			return null;
		if (a.length() != b.length())
			return Collections.emptyList();

		List<String> list = new ArrayList<>();
		cache.clear();
		for (int i = 0; i < a.length(); i++)
			if (a.charAt(i) != b.charAt(i))
				cache.add(i);
		discover(list, a, b);
		cache.clear();
		return list;
	}


	private static void discover(List<String> list, String a, String b)
	{
		int j = 0;
		for (int i : cache)
		{
			Object[] near = null;
			TreeSet<Object[]> neighborAt = getNeighborAt(get(a), i);
			if (neighborAt != null)
				for (Object[] node : neighborAt)
					if (getValue(node).charAt(i) == getValue(get(b)).charAt(i))
						near = node;
			if (near != null)
			{
				list.add(getValue(near));
				cache.remove(i);
				if (j > 0)
				{
					discover(list, a, b);
					return;
				}
			}
			++j;
		}
	}
}
