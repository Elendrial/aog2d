package me.elendrial.aog2d.gameSystems.turns;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;

public class TurnController {
	
	// This is essentially the TickController, but rather than running on it's own and running continuously, it just forwards events to other parts of the game.
	// May remove, not strictly needed. Can be implemented as part of the AoGLevel
	
	private Player[] players;
	private int playerTurn;
	private AoGLevel parentLevel;
	
	public TurnController(Player[] players, AoGLevel level) {
		this.players = players;
		playerTurn = 0;
		parentLevel = level;
	}
	
	public Player getCurrentPlayer() {
		return players[playerTurn];
	}
	
	public void nextTurn() {
		endTurn();
		playerTurn ++;
		playerTurn %= players.length;
		beginTurn();
	}
	
	private void endTurn() {
		parentLevel.clickController.deselect(getCurrentPlayer());
	}
	
	private void beginTurn() {
		// Collect Mana, move to player's Chief Barbarian/first portal/etc
	}
	
}
