import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A basic implementation of StackResize
 *
 * @author ci010
 */
public class StackResize<T> implements Iterable<T>
{
	private T[] data;
	private int pointer, size;

	public StackResize()
	{
		size = 20;
		data = (T[]) new Object[size];
		pointer = -1;
	}

	/**
	 * @return If stack is empty;
	 */
	public boolean isEmpty()
	{
		return data == null || pointer == -1;
	}

	/**
	 * @param data Push a new element into stack.
	 */
	public void push(T data)
	{
		if (pointer >= size - 1)
		{
			T[] temp = this.data;
			this.size = size * 2;
			this.data = (T[]) new Object[size];
			for (int i = 0; i < temp.length; ++i)
				this.data[i] = temp[i];
		}
		this.data[++pointer] = data;
	}

	/**
	 * Pop out the latest element pushed into this stack.
	 *
	 * @return The latest element pushed into this stack.
	 */
	public T pop()
	{
		if (this.isEmpty())
			throw new NoSuchElementException("StackResize is empty");
		if (pointer <= size / 4)
		{
			T[] temp = this.data;
			this.data = (T[]) new Object[size /= 4];
			for (int i = 0; i < temp.length; ++i)
				this.data[i] = temp[i];
		}
		return data[pointer--];
	}

	/**
	 * @return The latest element pushed into this stack.
	 */
	public T peek()
	{
		if (this.isEmpty())
			throw new NoSuchElementException("StackResize is empty");
		return data[pointer];
	}

	/**
	 * @return The first element in the stack
	 */
	public T firstElement()
	{
		return data[0];
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			int current = pointer;

			@Override
			public boolean hasNext()
			{
				return current > -1;
			}

			@Override
			public T next()
			{
				return data[current--];
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException("Not support remove.");
			}
		};
	}
}
