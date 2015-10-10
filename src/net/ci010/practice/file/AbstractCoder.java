package net.ci010.practice.file;

import java.io.File;
import java.util.Map;

public abstract class AbstractCoder implements Coder
{
	final Map<String, String> mapper;
	String path;
	String extension;
	StringBuffer name;

	AbstractCoder(Map<String, String> mapper)
	{
		this.mapper = mapper;
	}

	@Override
	public String getResult()
	{
		if (extension.equals("-") || extension.equals(""))
		{
			System.out.println(path + name.toString() + extension);
			return path + name.toString() + extension;
		}
		else
		{
			return path + name.toString() + "." + extension;
		}
	}

	@Override
	public void shift()
	{
		if (isRunnable())
			for (int i = 0; i < name.length(); i++)
			{
				char c = name.charAt(i);

				if (c == '.')
				{
					name.deleteCharAt(i);
					i--;
					continue;
				}
				if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90) || (c >= 48 && c <= 57) || (c > 1867))
					c = warpChar(c);

				name.setCharAt(i, c);
			}
	}

	public abstract char warpChar(char c);

	@Override
	public void accpet(File file)
	{
		String fileName = file.getName();
		int dot = fileName.lastIndexOf(".");

		if (dot == -1)
		{
			int line = fileName.lastIndexOf('-');
			if (line == -1)
			{
				path = file.getParent()+"/";
				name = new StringBuffer(fileName);
				extension = "";
			}
			else
			{
				path = file.getParent()+"/";
				name = new StringBuffer(fileName.substring(0, line));
				extension = "-";
			}
			return;
		}
		path = file.getParent() + "\\";
		extension = fileName.substring(dot + 1, fileName.length());
		name = new StringBuffer(fileName.substring(0, dot));

	}

	public abstract boolean isRunnable();

}
