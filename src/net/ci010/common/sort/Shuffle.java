package net.ci010.common.sort;

import net.ci010.common.Sorter;
import net.ci010.practice.learning.CompareUtil;

import java.util.Comparator;
import java.util.Random;

/**
 * @author ci010
 */
public class Shuffle implements Sorter
{
	@Override
	public <T> void sort(T[] arr, Comparator<T> comparator)
	{
		Random r = new Random();
		int N = arr.length;
		for (int i = 0, next = 0; i < N; next = r.nextInt(++i))
			if (i != next)
				CompareUtil.exch(arr, i, next);
	}
}
