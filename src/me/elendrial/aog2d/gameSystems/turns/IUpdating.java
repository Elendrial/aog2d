package me.elendrial.aog2d.gameSystems.turns;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;
import me.hii488.handlers.LevelHandler;

public interface IUpdating {
	
	public default void onTurnStart(Player playerTurn) {}
	public default void onTurnEnd(Player playerTurn) {}
	
	public default void registerAsUpdating() {
		registerAsUpdating((AoGLevel) LevelHandler.getCurrentLevel());
	}
	
	public default void registerAsUpdating(AoGLevel l) {
		l.turnController.registerIUpdating(this);
	}
	
	public default void unregisterAsUpdating() {
		unregisterAsUpdating((AoGLevel) LevelHandler.getCurrentLevel());
	}
	
	public default void unregisterAsUpdating(AoGLevel l) {
		l.turnController.unregisterIUpdating(this);
	}
	
}
