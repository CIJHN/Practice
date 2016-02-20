/**
 * This class control the actual game data of the game.
 * It also record the history of player's action.
 *
 * @author ci010
 */
public enum GameData
{
	/**
	 * The instance of the Game data.
	 * Since it should be a singleton, I use enum to write this class.
	 */
	INSTANCE;

	private StackResize<State> history = new StackResize<>();

	public void restart()
	{
		State temp = history.firstElement();
		history = new StackResize<>();
		this.transfer(temp);
	}

	/**
	 * Transfer the game into another state.
	 *
	 * @param state The state will be transferred into.
	 */
	public void transfer(State state)
	{
		this.history.push(state);
	}

	/**
	 * Cancel the last action player had.
	 */
	public void backPort()
	{
		if (!history.isEmpty())
		{
			System.out.println("There is no previous step! Cannot cancel the action.");
			return;
		}
		this.history.pop();
	}

	/**
	 * @return The current state of the game.
	 */
	public State getCurrentState()
	{
		return history.peek();
	}
}
