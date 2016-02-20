package net.ci010.homework;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author ci010
 */
public class WCount
{
	public static void main(String[] args)
	{
		WCount wCount = new WCount();
		try
		{
			Scanner scan;
			scan = new Scanner(System.in);
//			scan = new Scanner(testSRC);
			while (scan.hasNext())
				wCount.add(scan.next());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(wCount);
	}

	///////////////////////////////////////
	//           Class Start             //
	///////////////////////////////////////

	private ResizedArray<Entry> data = new ResizedArray<Entry>();
	private Sorter<Entry> sorter = new Insertions<Entry>();

	public void add(String word)
	{
		word = word.toLowerCase();
		char last = word.charAt(word.length() - 1);
		if (last == ',' || last == '.')
			word = word.substring(0, word.length() - 1);
		Entry entry = this.getEntry(word);
		if (entry == null)
			data.add(new Entry(word));
		else
			++entry.value;
	}

	private Entry getEntry(String key)
	{
		for (Entry entry : this.data)
			if (entry.getKey().equals(key))
				return entry;
		return null;
	}

	@Override
	public String toString()
	{
		this.sorter.sort(this.data.arr, new Comparator<Entry>()
		{
			@Override
			public int compare(Entry o1, Entry o2)
			{
				if (o1 == null || o2 == null)
					return 1;
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		StringBuilder builder = new StringBuilder();
		for (Entry entry : this.data)
			builder.append(entry).append("\n");
		return builder.toString();
	}

	///////////////////////////////////////
	//            Class End              //
	///////////////////////////////////////


	///////////////////////////////////////
	//        Nested Class Start         //
	///////////////////////////////////////

	public class Entry
	{
		private String key;
		private int value;

		public Entry(String key)
		{
			this.key = key;
			this.value = 1;
		}

		public String getKey()
		{
			return key;
		}

		public int getValue()
		{
			return value;
		}

		@Override
		public boolean equals(Object o)
		{
			if (o instanceof Entry)
			{
				Entry entry = (Entry) o;
				return entry.key.equals(this.key);
			}
			return super.equals(o);
		}

		@Override
		public String toString()
		{
			return key.concat(" ") + value;
		}
	}

	static class ResizedArray<T> implements Iterable<T>
	{
		private T[] arr;
		private int index, size;

		public ResizedArray()
		{
			this(10);
		}

		public ResizedArray(int size)
		{
			arr = this.newArray(size);
			this.size = size;
			this.index = 0;
		}

		private T[] newArray(int size)
		{
			Object[] objects = new Object[size];
			return (T[]) objects;
		}

		public void add(T element)
		{
			if (index >= size - 1)
			{
				T[] temp = newArray(this.size * 2);
				System.arraycopy(this.arr, 0, temp, 0, this.size);
				this.size *= 2;
				arr = temp;
			}
			arr[index++] = element;
		}

		public T get(int idx)
		{
			if (idx < 0 || idx >= this.size)
				throw new IllegalArgumentException(String.format("The array has only %s element", this.size));
			return arr[idx];
		}

		public boolean contains(Object element)
		{
			for (T t : this.arr)
				if (!t.equals(element))
					return false;
			return true;
		}

		public int size()
		{
			return this.size;
		}

		public T[] toArray()
		{
			T[] temp = newArray(this.size);
			System.arraycopy(arr, 0, temp, 0, this.size);
			return temp;
		}

		@Override
		public Iterator<T> iterator()
		{
			return new Iterator<T>()
			{
				private int size = ResizedArray.this.index;
				private int current = 0;

				@Override
				public boolean hasNext()
				{
					return current < size;
				}

				@Override
				public T next()
				{
					return get(current++);
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException("Not support remove.");
				}
			};
		}
	}

	interface Sorter<Type>
	{
		void sort(Type[] arr, Comparator<Type> comparator);
	}

	public static class Insertions<Type> implements Sorter<Type>
	{
		@Override
		public void sort(Type[] arr, Comparator<Type> comparator)
		{
			for (int i = 0; i < arr.length - 1; i++)
			{
				Type min = arr[i];
				int idxMin = i;
				for (int j = i; j < arr.length; j++)
					if (comparator.compare(arr[j], min) < 0)
					{
						min = arr[j];
						idxMin = j;
					}
				arr[idxMin] = arr[i];
				arr[i] = min;
			}
		}
	}


	static String testSRC = "This may be the way the world ends not with\n" +
			"a bang\n" +
			"but with a temper tantrum.\n" +
			"Okay, a temporary government shutdown\n" +
			"which became almost inevitable\n" +
			"after Sunday's House vote to provide government\n" +
			"\n" +
			"funding only on unacceptable conditions\n" +
			"wouldn't be the end of the world.\n" +
			"But a United States government default,\n" +
			"which will happen unless Congress raises\n" +
			"the debt ceiling soon,\n" +
			"might cause financial catastrophe.\n" +
			"\n" +
			"Unfortunately, many Republicans either don't\n" +
			"understand\n" +
			"this or don't care.\n";


}
