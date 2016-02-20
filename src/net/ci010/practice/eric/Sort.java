package net.ci010.practice.eric;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author ci010
 */
public class Sort
{
	public static void main(String[] args)
	{
		Scanner user = new Scanner(System.in);
		System.out.println("Enter numbers you would like to sort.");
		double[] array = new double[10];
		for (int a = 0; a < array.length; a++)
			array[a] = user.nextDouble();
		for (int i = 0; i < array.length; i++)
			for (int a = 0; a < array.length - 1; a++)
				if (array[a] > array[a + 1])
				{
					double temp;
					temp = array[a];
					array[a] = array[a + 1];
					array[a + 1] = temp;
				}
		for (double v : array)
			System.out.println(v);
	}

	public static <T> T[] switchElment(T[] arr, int first, int second)
	{
		T temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
		return arr;
	}

	public static int[] switchElment(int[] arr, int first, int second)
	{
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
		return arr;
	}

	public static double[] switchElment(double[] arr, int first, int second)
	{
		double temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
		return arr;
	}

	public static <T> T[] newArray(int size)
	{
		Object[] o = new Object[size];
		return (T[]) o;
	}
}
