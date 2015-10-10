package net.ci010.practice.alex;

import java.util.EnumMap;

public class BoradGame
{
	int rounds = 0; // count the rounds

	private ChessBoard board;

	Situation situation;

	GameRule rule;

	Controller a, b;

	EnumMap<?, Integer> possibleChess;
	EnumMap<Side, ?> side;

	BoradGame(Controller a, Controller b, GameRule rule)
	{
		this.a = a;
		this.b = b;
		this.rule = rule;

		situation = Situation.nothing;

		board = new ChessBoard(rule.getSize());

		possibleChess = rule.getPossibleChessType();
	}

	public enum Situation
	{
		nothing, danger, win, lost;
	}

	public enum Side
	{
		a, b
	}

	public void start(boolean side)
	{
		pickSide();

		if (!side)
			round(b);

		while (situation == Situation.nothing || situation == Situation.danger)
		{
			round(a);
			round(b);
		}
	}

	private void pickSide()
	{
		// TODO 自动生成的方法存根

	}

	private void round(Controller ctrl)
	{
		Action playerMove = rule.getAction(ctrl);

		if (rule.isValidAction(playerMove))
		{
			rounds++;
			rule.applyActionOn(board, playerMove);
		}
	}
	

	// Pos getPlayerMove(Controller a)
	// {
	// return new Pos(TicTacToe3.getIntInput("Enter row(1 to 3): ") - 1,
	// TicTacToe3.getIntInput("Enter column(1 to 3): ") - 1);
	// }

}