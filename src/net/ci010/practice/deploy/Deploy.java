package net.ci010.practice.deploy;

import java.io.IOException;

/**
 * @author ci010
 */
public class Deploy
{
	public static void apply(String path)
	{

		try
		{
			Runtime.getRuntime().exec("gradlew setupDevWorkspace idea");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
