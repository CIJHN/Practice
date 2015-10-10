package net.ci010.practice.grammar;

public class Sentence
{
	String content;
	String[] token;
	String verb;
	
	boolean isSpecialWord(String s)
	{
		return Helper.orEqual(s, "The", "the", "a", "A");
	}
}
