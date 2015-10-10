package net.ci010.practice.learning;

public class CompareUtil
{
	@SuppressWarnings("unchecked")
	public static <T> boolean less(Comparable<T> comparable, Comparable<T> comparable2)
	{
		return comparable.compareTo((T) comparable2) < 0;
	}

	public static <T> void exch(Comparable<T>[] a, int i, int min)
	{
		Comparable<T> temp = a[i];
		a[i] = a[min];
		a[min] = temp;
	}
}
