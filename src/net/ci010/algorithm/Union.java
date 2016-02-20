package net.ci010.algorithm;

/**
 * @author ci010
 */
public interface Union
{
	void connect(int p, int q);

	boolean isConnect(int q, int p);

	int get(int p);

	int count();
}
