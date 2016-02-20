package net.ci010.practice;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author ci010
 */
public class SimpleSort
{
	public static void sortFile(String name)
	{
		try
		{
			Base64 base64;

			File target = new File(name);
			Scanner scan = new Scanner(target);
			List<String> list = new ArrayList<>();
			while (scan.hasNext())
			{
				String next = scan.next();
				if (next.contains(","))
					list.addAll(Arrays.asList(next.split(",")));
				else
					list.add(next);
			}
			scan.close();
			Collections.sort(list);
			File out = new File(target.getParent(), "result.txt");
			if (!out.exists()) out.createNewFile();
			FileWriter writer = new FileWriter(out);
			for (String s : list)
				writer.write(s.concat(" "));
			writer.close();

			//Sample http://www.85porn.net/media/videos/iphone/8067.mp4?st=1cX4mn_l6HxbQW7NCl-uHQ&e=1448184564
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
