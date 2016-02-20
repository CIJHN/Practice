package net.ci010.algorithm;

/**
 * @author ci010
 */
public abstract class UnionArrBase implements Union
{
	protected int[] id;
	private int size;

	public UnionArrBase(int size)
	{
		this.size = size;
		id = new int[size];
		for (int i = 0; i < id.length; i++)
			id[i] = i;
	}

	@Override
	public int count()
	{
		return size;
	}
}
