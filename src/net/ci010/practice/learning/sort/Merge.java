package net.ci010.practice.learning.sort;

import net.ci010.practice.learning.CompareUtil;

public class Merge implements Sort
{
	@Override
	public <T> void sort(Comparable<T>[] a)
	{
		@SuppressWarnings("unchecked")
		Comparable<T>[] aux = (Comparable<T>[]) new Object[a.length];
		sort(a,aux,0,a.length-1);
	}

	private <T> void sort(Comparable<T>[] a, Comparable<T>[] aux, int left, int right)
	{
		if (left <= right)
			return;
		int mid = (right - left) / 2;
		sort(a, aux, left, mid);
		sort(a, aux, mid + 1, right);
		merge(a, aux, left, mid, right);
	}

	private <T> void merge(Comparable<T>[] a, Comparable<T>[] aux, int left, int mid, int right)
	{
		if (CompareUtil.less(a[left], a[right]))
			return;
		
		for (int i = left; i < (right - left); i++)
			aux[i] = a[i];
		
		int leftIndex = left, rightIndex = mid + 1;
		for (int i = left; i < (right - left); i++)
			if (leftIndex > mid)
				a[i] = aux[rightIndex++];
			else if (rightIndex > right)
				a[i] = aux[leftIndex++];
			else if (CompareUtil.less(aux[leftIndex], aux[rightIndex]))
				a[i] = aux[leftIndex++];
			else
				a[i] = aux[rightIndex++];

	}
}
