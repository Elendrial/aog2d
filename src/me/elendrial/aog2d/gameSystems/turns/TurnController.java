package me.elendrial.aog2d.gameSystems.turns;

public class TurnController {
	
	// This is essentially the TickController, but rather than running on it's own and running continuously, it just forwards events to other parts of the game.
	// May remove, not strictly needed. Can be implemented as part of the AoGLevel
	
	private int amountOfPlayers;
	private int playerTurn;
	
	public TurnController(int amountOfPlayers) {
		this.amountOfPlayers = amountOfPlayers;
		playerTurn = 0;
	}
	
	public void nextTurn() {
		endTurn();
		playerTurn ++;
		playerTurn %= amountOfPlayers;
		beginTurn();
	}
	
	private void endTurn() {
		
	}
	
	private void beginTurn() {
		// Collect Mana
	}
	
}
