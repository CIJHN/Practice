package net.ci010.algorithm;

/**
 * @author ci010
 */
public class UFQuick extends UnionArrBase
{
	public UFQuick(int size)
	{
		super(size);
	}

	/**
	 * 2n
	 *
	 * @param p
	 * @param q
	 */
	@Override
	public void connect(int p, int q)
	{
		int rootP = get(p);
		int rootQ = get(q);
		id[rootP] = rootQ;
	}

	/**
	 * 2n
	 *
	 * @param q
	 * @param p
	 * @return
	 */
	@Override
	public boolean isConnect(int q, int p)
	{
		return get(p) == get(q);
	}

	/**
	 * n
	 *
	 * @param p
	 * @return
	 */
	@Override
	public int get(int p)
	{
		while (p != id[p]) p = id[p];
		return 0;
	}
}
