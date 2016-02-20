package net.ci010.practice.alex.matrix;

import java.util.Random;

/**
 * @author ci010
 */

public class MagMatrixHandler
{
	private Random r = new Random();

	public static void main(String[] args)
	{
		int runTimes = 100000;
		double mandE[][] = new double[2][50];
		double me[];
		for (int i = 0; i < 50; i++)
		{
			MagMatrixHandler mag = new MagMatrixHandler(50, 0.2 + 0.2 * i, 1.0);
			me = mag.run(runTimes);
			mandE[0][i] = me[0];
			mandE[1][i] = me[1];
			System.out.println(mag);
			System.out.printf("energy: %3f, mean: %3f\n", me[0], me[1]);
			System.out.println(mag.less + " " + mag.more);

			System.out.println("");
		}
	}

	public int less, more;
	private MagMatrix matrix;
	private double temperature;
	private double beta;
	private double totalEnergy;
	private double energyDelta;

	public MagMatrixHandler(int latticeSize, double temperature, double energyDelta)
	{
		this.matrix = new MagMatrix(latticeSize);
		//Set up the array with size latticeSize plus 2
		//with a boundary of all zero.
		this.temperature = temperature;
		this.beta = 1 / temperature;
		this.energyDelta = energyDelta;
		this.totalEnergy = this.getRegionEnergy(1, 1, latticeSize, latticeSize);
		System.out.println(test() + " " + totalEnergy);
	}


	public double test()
	{
		double TotalE = 0;
		double spins[] = new double[5];
		double spinNeighbor;
		for (int j = 1; j <= matrix.size(); j++)
		{
			for (int i = 1; i <= matrix.size(); i++)
			{
				spins[0] = matrix.get(i + 1, j);
				spins[1] = matrix.get(i - 1, j);
				spins[2] = matrix.get(i, j - 1);
				spins[3] = matrix.get(i, j + 1);
				spins[4] = matrix.get(i, j);

				for (int k = 0; k < 4; k++)
				{
					spinNeighbor = Math.abs(spins[k] + spins[4]);
					if (spinNeighbor == 0)
					{
						TotalE = TotalE + this.energyDelta;
					}
					else if (spinNeighbor == 2)
					{
						TotalE = TotalE - this.energyDelta;
					}
				}

			}
		}
		return TotalE;
	}

	/**
	 * Take a random monteCarloStep and flip the sign of the magniti that position.
	 */
	private void monteCarloStep()
	{
		int x = r.nextInt(this.matrix.size()) + 1, y = r.nextInt(matrix.size() + 1) + 1;
		int minX = Math.max(x - 2, 1), minY = Math.max(y - 2, 1), maxX = Math.min(x + 2, matrix.size()), maxY = Math
				.min(y + 2, matrix.size());
		double lastRegionE = this.getRegionEnergy(minX, minY, maxX, maxY);
		matrix.flip(x, y);
		double delta = lastRegionE - this.getRegionEnergy(minX, minY, maxX, maxY);
		if (delta < 0)
			++less;
		else if (delta > 0)
			++more;
		if (delta >= 0 && Math.exp(-delta * this.beta) < Math.random())
			matrix.flip(x, y);
		this.totalEnergy += delta;
	}

	private double getRegionEnergy(int minX, int minY, int maxX, int maxY)
	{
		double energy = 0;
		for (int i = minX; i <= maxX; ++i)
			for (int j = minY; j <= maxY; ++j)
				energy += this.getEnergyAt(i, j);
		return energy;
	}

	private double getEnergyAt(int i, int j)
	{
		double energy = 0;
		byte spinNeighbor;
		byte spins[] = new byte[5];
		spins[0] = matrix.get(i + 1, j);
		spins[1] = matrix.get(i - 1, j);
		spins[2] = matrix.get(i, j - 1);
		spins[3] = matrix.get(i, j + 1);
		spins[4] = matrix.get(i, j);
		for (int k = 0; k < 4; k++)
		{
			spinNeighbor = (byte) (spins[k] + spins[4]);
			if (spinNeighbor == 0)
				energy += this.energyDelta;
			else //if (spinNeighbor == 2)
				energy -= this.energyDelta;
		}
		return energy / 2;
	}

	@Override
	public String toString()
	{
		return String.format("Temperature: %3f, Mean: %3f, Energy: %3f", this.temperature, this.matrix.mean(), this
				.totalEnergy);
	}

	public double[] run(int times)
	{
		double allSteps[][] = new double[2][times];
		for (int i = 0; i < times; i++)
		{
			allSteps[0][i] = this.totalEnergy;
			allSteps[1][i] = this.matrix.mean();
			this.monteCarloStep();
		}
		double equilibriumState[] = new double[2];
		//Loop through last 70% steps.
		for (int i = (int) (0.3 * times); i < times; i++)
		{
			equilibriumState[0] += allSteps[0][i];
			equilibriumState[1] += allSteps[1][i];
		}
		//cal average
		equilibriumState[0] = equilibriumState[0] / (0.7 * times);
		equilibriumState[1] = equilibriumState[1] / (0.7 * times);
		return equilibriumState;
	}
}
