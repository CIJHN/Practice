package net.ci010.practice.file;

import java.util.Map;

public class Decoder extends AbstractCoder implements Coder
{
	Decoder(Map<String, String> mapper)
	{
		super(mapper);
	}

	@Override
	public char warpChar(char c)
	{
		if (c == '0')
			c = '9';
		else if (c == 'a')
			c = 'z';
		else if (c == 'A')
			c = 'Z';
		else
			c = (char) (c - 1);

		return c;
	}

	@Override
	public boolean isRunnable()
	{
		return mapper.containsValue(extension);
	}

	@Override
	public void replace()
	{
		if (isRunnable())
			for (String key : mapper.keySet())
				if (mapper.get(key).equals(extension))
					extension = key;
	}
}
