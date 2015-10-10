package net.ci010.practice.alex;

import java.util.EnumMap;

public interface GameRule
{
	Size getSize();

	EnumMap<?, Integer> getPossibleChessType();

	boolean isValidAction(Action playerMove);

	Situation determine(Controller player);

	void applyActionOn(ChessBoard board, Action playerMove);

	Action getAction(Controller ctrl);
}
