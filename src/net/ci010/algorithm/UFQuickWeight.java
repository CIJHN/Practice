package net.ci010.algorithm;

import java.util.Arrays;

/**
 * @author ci010
 */
public class UFQuickWeight extends UFQuick
{
	private int[] weight;

	public UFQuickWeight(int size)
	{
		super(size);
		weight = new int[size];
		Arrays.fill(weight, 1);
	}

	/**
	 * lgN
	 *
	 * @param p
	 * @param q
	 */
	@Override
	public void connect(int p, int q)
	{
		int pRoot = this.get(p);
		int qRoot = this.get(q);
		if (pRoot == qRoot)
			return;
		if (weight[pRoot] > weight[qRoot])
		{
			id[qRoot] = pRoot;
			weight[pRoot] += weight[qRoot];
		}
		else
		{
			id[pRoot] = qRoot;
			weight[qRoot] += weight[pRoot];
		}
	}

	/**
	 * lgN
	 *
	 * @param q
	 * @param p
	 * @return
	 */
	@Override
	public boolean isConnect(int q, int p)
	{
		return super.isConnect(q, p);
	}

	/**
	 * lgN
	 *
	 * @param p
	 * @return
	 */
	@Override
	public int get(int p)
	{
		while (p != id[p])//if this is not point to root
		{
			id[p] = id[id[p]];//make this pointer point to the target of target of this pointer
			p = id[p];
		}
		return 0;
	}
}
