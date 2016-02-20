package net.ci010.algorithm;

import java.util.Scanner;

/**
 * @author ci010
 */
public class UFTest
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner("");
		int num = scanner.nextInt();
		UFLazy uf = new UFLazy(num);
		while (scanner.hasNext())
		{
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			if (!uf.isConnect(p, q))
			{
				uf.connect(p, q);
				System.out.println(p + " " + q);
			}
		}
	}
}
