package net.ci010.practice.random;

import java.util.Random;

public class RandomTest
{
	public static final Random r = new Random();

	private static int generateInRange(int num)
	{
		int temp = r.nextInt(num);

		return temp < num / 4 ? generateInRange(num) : temp > (num / 3) * 2 ? generateInRange(num) : temp;
	}

	public static float[] generateTalent(int num)
	{
		float[] arr = new float[num];

		for (int i = 0; i < num; i++)
		{
			int ten;
			int unit;
			int possibility = r.nextInt(40);

			if (possibility == 0)
				ten = 2;
			else
				ten = 1;

			if (ten == 1)
				unit = r.nextInt(9) + 1;
			else
				unit = r.nextInt(4) + 1;
			arr[i] = (float) (ten + unit * 0.1);
		}
		
		return arr;
	}

	public static int[] generateInitValue(int num)
	{
		int sum = (int) (r.nextGaussian() * (double) num * 2.5d) + num * 10;

		int[] arr = new int[num++];

		for (int i = 0; i < num; i++)
			if (i == num - 1)
				break;
			else
			{
				int value = generateInRange(sum);
				arr[i] = value + 11;
				sum -= value;
			}

		return arr;
	}

	public static int[] generateLimitValue(int num)
	{
		int sum = (int) (r.nextGaussian() * (double) (num - 1) * 10d) + (num - 1) * 100;

		int[] arr = new int[num++];

		for (int i = 0; i < num; i++)
			if (i == num - 1)
				break;
			else
			{
				int value = generateInRange(sum);
				arr[i] = value + 101;
				sum -= value;
			}

		return arr;
	}
}
