package net.ci010.homework.cmps12b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import net.ci010.homework.cmps12b.BusinessSearch.Searcher.ElementNotFoundException;

public class BusinessSearch
{
	static Business[] business;
	static Sorter<Business> sorter = new Merge<Business>();
	static Searcher<Business> searcher = new BinarySearch<Business>();

	public static void main(String[] args)
	{
		if (args == null || args.length == 0)
			System.out.println("Usage: BusinessSearch businessDB");
		else
		{
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new FileReader(args[0]));
			}
			catch (FileNotFoundException e)
			{
				System.err.println("File named " + args[0] + "not found!");
			}

			if (preLoad(reader))
			{
				int notFound = 0, total = 0;
				Scanner scan = new Scanner(System.in);
				while (scan.hasNextLine())
				{
					String input = scan.next();
					if (!input.equals(""))
						try
						{
							System.out.println(getPhone(input));
							++total;
						}
						catch (ElementNotFoundException e)
						{
							System.err.println("NOT FOUND");
							++notFound;
						}
					else
					{
						System.out.println(total + " total queries, " + notFound + " not found");
						break;
					}
				}
				scan.close();
			}
		}
	}

	static boolean preLoad(BufferedReader reader)
	{
		if (reader != null)
		{
			try
			{
				business = new Business[Integer.valueOf(reader.readLine())];
			}
			catch (NumberFormatException | IOException e1)
			{
				System.err.println("The first number should be a number that shows the number of data!");
				e1.printStackTrace();
				return false;
			}

			String line = null;
			int idx = 0;
			try
			{
				while ((line = reader.readLine()) != null)
					business[idx++] = parseLine(line);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}

			sorter.sort(business);

			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	static Business parseLine(String line)
	{
		return new Business(line.split(","));
	}

	static class Business implements Comparable<Business>
	{
		String name;
		String phone;

		public Business(String... info)
		{
			this.name = info[0];
			if (info.length > 1)
				this.phone = info[1];
		}

		@Override
		public int compareTo(Business b)
		{
			return this.name.compareTo(b.name);
		}
	}

	static abstract class CustomDataStruct<Type extends Comparable<Type>>
	{
		Type[] data;
		Sorter<Type> sorter;
		Searcher<Type> searcher;
	}

	static String getPhone(String name) throws ElementNotFoundException
	{
		return searcher.search(business, new Business(name)).phone;
	}

	// static String getBusiness(String phone)
	// {
	// return searchBusiness(name, 0, business.length - 1).phone;
	// }

	interface Sorter<Type extends Comparable<Type>>
	{
		void sort(Type[] arr);
	}

	interface Searcher<Type extends Comparable<Type>>
	{
		Type search(Type[] arr, Type key) throws ElementNotFoundException;

		public static class ElementNotFoundException extends Exception
		{
			private static final long serialVersionUID = -7351545329213128896L;
		}
	}

	static class BinarySearch<Type extends Comparable<Type>> implements Searcher<Type>
	{
		@Override
		public Type search(Type[] arr, Type key) throws ElementNotFoundException
		{
			Type element = search(arr, key, 0, arr.length);
			if (element != null)
				return search(arr, key, 0, arr.length);
			else
				throw new ElementNotFoundException();
		}

		private Type search(Type[] arr, Type key, int left, int right)
		{
			if (left <= right)
				return null;
			int mid = (right + left) / 2;
			Type temp = arr[mid];
			int result = temp.compareTo(key);
			if (result == 0)
				return temp;
			else if (result > 0)
				return search(arr, key, mid + 1, right);
			else
				return search(arr, key, left, mid);
		}
	}

	@SuppressWarnings("unchecked")
	static class Merge<Type extends Comparable<Type>> implements Sorter<Type>
	{
		@Override
		public void sort(Type[] arr)
		{
			Comparable<Type>[] aux = (Comparable<Type>[]) new Object[arr.length];
			this.sort(arr, aux, 0, arr.length - 1);
		}

		private void sort(Comparable<Type>[] arr, Comparable<Type>[] aux, int left, int right)
		{
			if (left <= right)
				return;
			int mid = (right + left) / 2;
			sort(arr, aux, left, mid);
			sort(arr, aux, mid + 1, right);
			merge(arr, aux, left, mid, right);
		}

		private void merge(Comparable<Type>[] arr, Comparable<Type>[] aux, int left, int mid, int right)
		{
			if (aux[left].compareTo((Type) aux[right]) > 0)
				return;

			int leftIdx = left, rightIdx = mid + 1;

			for (int i = left; i < right; i++)
				aux[i] = arr[i];

			for (int i = left; i < right; i++)
				if (leftIdx > mid)
					arr[i] = aux[rightIdx++];
				else if (rightIdx > right)
					arr[i] = aux[leftIdx++];
				else if (aux[leftIdx].compareTo((Type) aux[rightIdx]) > 0)
					arr[i] = aux[rightIdx++];
				else
					arr[i] = aux[leftIdx++];
		}
	}
}
