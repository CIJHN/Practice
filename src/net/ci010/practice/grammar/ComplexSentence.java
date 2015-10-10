package net.ci010.practice.grammar;

public class ComplexSentence extends Sentence
{
	Sentence[] subSentence;

	public ComplexSentence(String s)
	{
		this.content=s;
		if (s.indexOf(",") != -1)
			token = s.split(" ");
	}

	

}
