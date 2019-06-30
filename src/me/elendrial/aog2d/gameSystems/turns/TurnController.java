package me.elendrial.aog2d.gameSystems.turns;

import java.util.ArrayList;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;

public class TurnController {
	
	// This is essentially the TickController, but rather than running on it's own and running continuously, it just forwards events to other parts of the game.
	// May remove, not strictly needed. Can be implemented as part of the AoGLevel
	
	private Player[] players;
	private int playerTurn;
	private AoGLevel parentLevel;
	private ArrayList<IUpdating> updating = new ArrayList<IUpdating>();
	
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
		for(IUpdating upd : updating) {
			upd.onTurnEnd(players[playerTurn]);
		}
	}
	
	private void beginTurn() {
		// Collect Mana, move to player's Chief Barbarian/first portal/etc
		for(IUpdating upd : updating) {
			upd.onTurnStart(players[playerTurn]);
		}
	}
	
	// Do not try to call these except through IUpdating.registerAsUpdating() and IUpdating.unregisterAsUpdating()
	protected void registerIUpdating(IUpdating u) {
		updating.add(u);
	}
	
	protected void unregisterIUpdating(IUpdating u) {
		updating.remove(u);
	}
	
}
