package net.ci010.practice.grammar;

public class Helper
{

	public static boolean orEqual(String s, String... target)
	{
		for (String t : target)
			if(s.equals(t))
				return true;
			else
				continue;
		return false;
	}

}
