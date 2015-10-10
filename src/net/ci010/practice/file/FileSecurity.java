package net.ci010.practice.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileSecurity
{
	private Map<String, String> extensionMap = new HashMap<String, String>();

	public FileSecurity(Map<String, String> map)
	{
		this.extensionMap = map;
	}
	
	public FileSecurity()
	{
		extensionMap.put("jpg", "tes");
		extensionMap.put("png", "te");
		extensionMap.put("rmvb", "bac");
		extensionMap.put("wmv", "arg");
		extensionMap.put("mkv", "stat");
		extensionMap.put("avi", "set");
		extensionMap.put("mp4", "attr");
		extensionMap.put("", "-");
	}

	public void encode(File file)
	{
		parse(file, new Encoder(extensionMap));
	}

	public void encode(String file)
	{
		encode(new File(file));
	}

	public void decode(String file)
	{
		decode(new File(file));
	}

	public void decode(File file)
	{
		parse(file, new Decoder(extensionMap));
	}

	protected void parse(File file, Coder coder)
	{
		if (file.isDirectory())
			for (File f : file.listFiles())
				parse(f, coder);

		coder.accpet(file);
		coder.shift();
		coder.replace();

		file.renameTo(new File(coder.getResult()));

	}

	@Deprecated
	protected StringBuffer shift(String s)
	{
		StringBuffer temp = new StringBuffer(s);
		for (int i = 0; i < temp.length(); i++)
		{
			char c = temp.charAt(i);
			if (c == '9')
				c = '0';
			else if (c == 'z')
				c = 'a';
			else
				c = (char) (c + 1);

			temp.setCharAt(i, c);
		}
		return temp;
	}

	@Deprecated
	protected StringBuffer deShift(String s)
	{
		StringBuffer temp = new StringBuffer(s);
		for (int i = 0; i < temp.length(); i++)
		{
			char c = temp.charAt(i);
			if (c == '9')
				c = '0';
			else if (c == 'z')
				c = 'a';
			else
				c = (char) (c - 1);

			temp.setCharAt(i, c);
		}
		return temp;
	}

	@Deprecated
	protected StringBuffer replace(String s) throws Exception
	{
		if (extensionMap.containsKey(s))
			return new StringBuffer(extensionMap.get(s));
		else if (extensionMap.containsValue(s))
			for (String key : extensionMap.keySet())
				if (extensionMap.get(key).equals(s))
					return new StringBuffer(key);
				else
					throw new Exception("not support type");

		throw new Exception("not support type");

	}

	@Deprecated
	public void decodeOld(File file)
	{
		if (file.isDirectory())
		{
			for (File f : file.listFiles())
			{
				decodeOld(f);
			}
		}
		else
		{
			String path = file.getParent() + "\\";
			String fileName = file.getName();
			int dot = fileName.lastIndexOf(".");
			String name = fileName.substring(0, dot);
			String back = fileName.substring(dot + 1, fileName.length());

			System.out.println(path);
			System.out.println(fileName);
			System.out.println(name);
			System.out.println(back);

			StringBuffer buff = new StringBuffer(path);
			try
			{
				buff.append(deShift(name));
				buff.append('.');
				buff.append(replace(back));
			}
			catch (Exception e)
			{
				return;
			}

			String result = buff.toString();
			file.renameTo(new File(result));
		}
	}

	@Deprecated
	public void encodeOld(File file)
	{
		if (file.isDirectory())
		{
			for (File f : file.listFiles())
			{
				encodeOld(f);
			}
		}
		else
		{
			String path = file.getParent() + "\\";
			String fileName = file.getName();
			int dot = fileName.lastIndexOf(".");
			String name = fileName.substring(0, dot);
			String back = fileName.substring(dot + 1, fileName.length());

			System.out.println(path);
			System.out.println(fileName);
			System.out.println(name);
			System.out.println(back);

			StringBuffer buff = new StringBuffer(path);
			try
			{
				buff.append(shift(name));
				buff.append('.');
				buff.append(replace(back));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return;
			}

			String result = buff.toString();
			file.renameTo(new File(result));
		}
	}
}
