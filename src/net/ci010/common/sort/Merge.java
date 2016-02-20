package net.ci010.common.sort;

import net.ci010.common.GenericUtil;
import net.ci010.common.Sorter;
import net.ci010.practice.learning.CompareUtil;
import net.ci010.practice.learning.sort.Sort;

import java.util.Comparator;

public class Merge implements Sorter
{
	private <T> void sort(Comparator<T> comparator, T[] a, T[] aux, int left, int right)
	{
		if (left <= right)
			return;
		int mid = (right - left) / 2;
		sort(comparator, a, aux, left, mid);
		sort(comparator, a, aux, mid + 1, right);
		merge(comparator, a, aux, left, mid, right);
	}

	private <T> void merge(Comparator<T> comparator, T[] a, T[] aux, int left, int mid, int
			right)
	{
		if (comparator.compare(a[left], a[right]) < 0)
			return;

		for (int i = left; i < (right - left); i++)
			aux[i] = a[i];

		int leftIndex = left, rightIndex = mid + 1;
		for (int i = left; i < (right - left); i++)
			if (leftIndex > mid)
				a[i] = aux[rightIndex++];
			else if (rightIndex > right)
				a[i] = aux[leftIndex++];
			else if (comparator.compare(aux[leftIndex], aux[rightIndex]) < 0)
				a[i] = aux[leftIndex++];
			else
				a[i] = aux[rightIndex++];
	}

	@Override
	public <T> void sort(T[] arr, Comparator<T> comparator)
	{
		T[] aux = GenericUtil.cast(new Object[arr.length]);
		sort(comparator, arr, aux, 0, arr.length - 1);
	}
}
