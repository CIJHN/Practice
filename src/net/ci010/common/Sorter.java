package net.ci010.common;

import java.util.Comparator;

/**
 * @author ci010
 */
public interface Sorter
{
	<T> void sort(T[] arr, Comparator<T> comparator);
}
