package net.ci010.practice.grammar;

public class Paragraph
{
	String content;
	// Sentence[] sentences;

	public Paragraph(String s)
	{
		this.content = s;
	}

	void parse()
	{

	}

	String[] parse(String type, String s)
	{
		return s.split(type);
	}
}
