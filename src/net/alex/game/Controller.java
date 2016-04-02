package net.ci010.practice.alex.game;

public interface Controller
{
	Action getAction();

	int getIntInput(String string);
	
	void getPossibleChess();
}
