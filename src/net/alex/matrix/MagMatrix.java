package net.ci010.practice.alex.matrix;

/**
 * @author ci010
 */
public class MagMatrix
{
	private int size, sum;
	private byte matrix[][];

	public MagMatrix(int size)
	{
		this.size = size;
		this.matrix = new byte[size + 2][size + 2];
		for (int i = 1; i <= size; i++)
			for (int j = 1; j <= size; j++)
				matrix[i][j] = 1;
		this.sum = size * size;
	}

	public void flip(int x, int y)
	{
		byte next;
		this.matrix[x][y] = next = (byte) -matrix[x][y];
		if (next > 0)
			this.sum += 2;
		else if (next < 0)
			this.sum -= 2;
	}

	public byte get(int x, int y)
	{
		return matrix[x][y];
	}

	public int size()
	{
		return this.size;
	}

	public int sum()
	{
		return sum;
	}

	public double mean()
	{
		return sum / (double) ((size - 2) * (size - 2));
	}
}
