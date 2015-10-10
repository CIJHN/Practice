package net.ci010.practice.learning.sort;

import static net.ci010.practice.learning.CompareUtil.*;

public class Selection implements Sort
{
	public <T> void sort(Comparable<T>[] a)
	{
		int N = a.length;
		for (int i = 0; i < N; i++)
		{
			int min = i;
			for (int j = i + 1; j < N; j++)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min);
		}
	}


}
