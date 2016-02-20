package net.ci010.common;

/**
 * @author ci010
 */
public interface StringParser<T>
{
	T parse(String value) throws IllegalArgumentException;
}
