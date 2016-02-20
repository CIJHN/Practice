package net.ci010.common.sort;

import net.ci010.common.Sorter;
import net.ci010.practice.learning.CompareUtil;

import java.util.Comparator;

public class Shell implements Sorter
{
	@Override
	public <T> void sort(T[] arr, Comparator<T> comparator)
	{
		int N = arr.length, h = 1;
		while (h < N / 3) h = h * 3 + 1;
		for (; h >= 1; h /= 3)
			for (int i = h; i < N; i++)
				for (int j = i; j >= h && CompareUtil.less(comparator, arr[j], arr[j + h]); j += h)
					CompareUtil.exch(arr, i, j);
	}
}
