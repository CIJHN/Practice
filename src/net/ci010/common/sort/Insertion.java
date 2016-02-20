package net.ci010.common.sort;

import net.ci010.common.Sorter;
import net.ci010.practice.learning.sort.Sort;

import java.util.Comparator;

import static net.ci010.practice.learning.CompareUtil.*;

public class Insertion implements Sorter
{
	@Override
	public <T> void sort(T[] arr, Comparator<T> comparator)
	{
		int N = arr.length;
		for (int i = 0; i < N; i++)
			for (int j = i; j > 0; j--)
				if (less(comparator, arr[j], arr[j - 1]))
					exch(arr, j, j - 1);
	}
}
