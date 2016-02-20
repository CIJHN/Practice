package net.ci010.common.search;

import net.ci010.common.Searcher;

import java.util.Comparator;

import static net.ci010.practice.learning.CompareUtil.*;

/**
 * @author ci010
 */
public class Binary implements Searcher
{
	@Override
	public <T> int search(T[] arr, T key, Comparator<T> comparator)
	{
		int low = 0, high = arr.length;
		while (low <= high)
		{
			int mid = low + (high - low) / 2;
			if (less(comparator, key, arr[mid])) high = mid - 1;
			else if (greater(comparator, key, arr[mid])) low = mid + 1;
			else return mid;
		}
		return -1;
	}
}
