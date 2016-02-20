package net.ci010.common;

/**
 * @author ci010
 */
@SuppressWarnings("unchecked")
public class GenericUtil
{
	public static <T> T cast(Object o)
	{
		return (T) o;
	}
}
