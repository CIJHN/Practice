package net.ci010.practice.learning;

import java.util.Comparator;

public class CompareUtil
{
	@SuppressWarnings("unchecked")
	public static <T> boolean less(Comparable<T> comparable, Comparable<T> comparable2)
	{
		return comparable.compareTo((T) comparable2) < 0;
	}

	public static <T> boolean greater(Comparator<T> comparator, T a, T b)
	{
		return comparator.compare(a, b) > 0;
	}

	public static <T> boolean less(Comparator<T> comparator, T a, T b)
	{
		return comparator.compare(a, b) < 0;
	}

	public static <T> void exch(T[] a, int i, int j)
	{
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
