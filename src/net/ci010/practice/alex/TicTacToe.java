package net.ci010.practice.alex;

import java.util.EnumMap;

import net.ci010.practice.alex.Situation;

public class TicTacToe implements GameRule
{

	@Override
	public Size getSize()
	{
		return new Size(3, 3);
	}

	enum Type
	{
		x, o, blank;
	}

	@Override
	public EnumMap<?, Integer> getPossibleChessType()
	{
		EnumMap<Type, Integer> ticTacToe = new EnumMap<Type, Integer>(Type.class);
		ticTacToe.put(Type.x, 1);
		ticTacToe.put(Type.o, 2);
		ticTacToe.put(Type.blank, -1);
		return ticTacToe;
	}

	@Override
	public boolean isValidAction(Action playerMove)
	{
		Size size = getSize();
		return playerMove.x < size.x && playerMove.y < size.y;
	}

	@Override
	public void applyActionOn(ChessBoard board, Action playerMove)
	{

	}

	@Override
	public Action getAction(Controller ctrl)
	{
		return new PlaceAction(ctrl, ctrl.getIntInput("Enter row(1 to 3): "), ctrl.getIntInput("Enter column(1 to 3): "));
	}

	@Override
	public Situation determine(Controller player)
	{
//		if (sum.equals("XXX"))
			return Situation.win;
//		else if (sum.equals("OOO"))
//			return Situation.lost;
//		else if (sum.equals("NOO") || sum.equals("OON") || sum.equals("NXX") || sum.equals("XXN") || sum.equals("ONO") || sum.equals("XNX"))
//			return Situation.danger;
	}

	class PlaceAction extends Action
	{
		public PlaceAction(Controller ctrl, int x, int y)
		{
			super(ctrl, x, y);
		}
	}

}
