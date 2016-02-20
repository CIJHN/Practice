package net.ci010.common;

import java.util.Comparator;

/**
 * @author ci010
 */
public interface Searcher
{
	<T> int search(T[] arr, T key, Comparator<T> comparator);
}
