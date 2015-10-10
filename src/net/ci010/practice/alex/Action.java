package net.ci010.practice.alex;

public class Action extends Tuple2D
{
	Controller ctrl;

	public Action(Controller ctrl, int x, int y)
	{
		this.ctrl = ctrl;
		this.x = x;
		this.y = y;
	}
}
