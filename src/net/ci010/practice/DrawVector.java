package net.ci010.practice;

import java.util.List;

/**
 * @author ci010
 */
public class DrawVector
{
	static Vector2f screenSize = new Vector2f(900, 900);

	public static void init()
	{
		StdDraw.setCanvasSize(screenSize.x, screenSize.y);
		StdDraw.setXscale(0, screenSize.x);
		StdDraw.setYscale(0, screenSize.y);
	}

	public static void draw(Vector2f pos, Vector2f size)
	{
		StdDraw.rectangle(pos.x - size.x / 2, pos.y - size.y / 2, size.x / 2, size.y /
				2);
	}

	public static void test()
	{
		init();
		draw(new Vector2f(850, 850), new Vector2f(50, 50));
		StdDraw.circle(850, 850, 30);
		Vector2f size = new Vector2f(100, 100);
		List<Vector2f> lst = GuiElementPosInfo.format(screenSize, size, 20, 10);
		for (Vector2f vector2f : lst)
		{
			draw(vector2f, size);
		}
	}
}
