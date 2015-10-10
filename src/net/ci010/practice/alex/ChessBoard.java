
package net.ci010.practice.alex;

public class ChessBoard
{
	int[][] tic;
	int length, height;

	public ChessBoard(Size size)
	{
		this.length = size.x;
		this.height = size.y;
		// tic = new Chess[size][size];
		//
		for (int j = 0; j < length; j++)
			for (int k = 0; k < height; k++)
				tic[j][k] = -1;
	}

	void put(Tuple2D pos, int value)
	{

	}

}
