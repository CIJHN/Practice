package net.ci010.common.sort;

import net.ci010.common.Sorter;

import java.util.Comparator;

import static net.ci010.practice.learning.CompareUtil.exch;
import static net.ci010.practice.learning.CompareUtil.less;

public class Selection implements Sorter
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


	@Override
	public <T> void sort(T[] arr, Comparator<T> comparator)
	{
		int N = arr.length;
		for (int i = 0; i < N; i++)
		{
			int min = i;
			for (int j = i + 1; j < N; j++)
				if (less(comparator, arr[j], arr[min]))
					min = j;
			exch(arr, i, min);
		}
	}
}
