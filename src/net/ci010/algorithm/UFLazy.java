package net.ci010.algorithm;

/**
 * n^2 time to connect.
 *
 * @author ci010
 */
public class UFLazy extends UnionArrBase implements Union
{
	public UFLazy(int size)
	{
		super(size);
	}

	/**
	 * n^2
	 *
	 * @param p
	 * @param q
	 */
	@Override
	public void connect(int p, int q)
	{
		if (isConnect(p, q))
			return;
		int value = id[q];
		int old = id[p];
		for (int i = 0; i < id.length; i++)
			if (id[i] == old)
				id[i] = value;
	}

	/**
	 * 1
	 *
	 * @param q
	 * @param p
	 * @return
	 */
	@Override
	public boolean isConnect(int q, int p)
	{
		return id[p] == id[q];
	}

	@Override
	public int get(int p)
	{
		return id[p];
	}
}
