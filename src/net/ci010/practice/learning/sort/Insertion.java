package net.ci010.practice.learning.sort;

import static net.ci010.practice.learning.CompareUtil.*;

public class Insertion implements Sort
{
	public <T> void sort(Comparable<T>[] a)
	{
		int N = a.length;
		for (int i = 0; i < N; i++)
		{
			for (int j = i; j > 0; j--)
				if (less(a[j], a[j - 1]))
					exch(a, j, j - 1);
				else
					break;
		}
	}
}
