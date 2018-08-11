package me.elendrial.aog2d.gameSystems.turns;

import me.elendrial.aog2d.gameSystems.players.Player;

public interface IUpdating {
	
	public default void onTurnStart(Player playerTurn) {}
	public default void onTurnEnd(Player playerTurn) {}
	
}
