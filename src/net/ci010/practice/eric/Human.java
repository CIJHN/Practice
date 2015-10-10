package net.ci010.practice.eric;

public class Human
{
	public static int population;
	private String name;
	private float height;
	private float weight;

	public Human(String name, float height, float weight)
	{
		this.name = name;
		this.height = height;
		this.weight = weight;
	}
	
	public Human()
	{
		this.name = "dummy";
		this.height = 170.0f;
		this.weight = 50.0f;
	}

	public void say(String content)
	{
		System.out.println(content);
	}

	public void introSelf()
	{
		System.out.println("My name is " + name);
	}
	
	public float getWeight()
	{
		return this.weight;
	}
	
	public float getHeight()
	{
		return this.height;
	}
}
