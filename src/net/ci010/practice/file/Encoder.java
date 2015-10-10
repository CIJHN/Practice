package net.ci010.practice.file;

import java.util.Map;

public class Encoder extends AbstractCoder implements Coder
{
	Encoder(Map<String, String> mapper)
	{
		super(mapper);
	}

	@Override
	public char warpChar(char c)
	{
		if (c == '9')
			c = '0';
		else if (c == 'z')
			c = 'a';
		else if (c == 'Z')
			c = 'A';
		else
			c = (char) (c + 1);

		return c;
	}

	@Override
	public boolean isRunnable()
	{
		return mapper.containsKey(extension);
	}

	@Override
	public void replace()
	{
		if (isRunnable())
			extension = mapper.get(extension);
	}

}
