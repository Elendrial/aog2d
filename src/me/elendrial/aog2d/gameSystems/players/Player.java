package me.elendrial.aog2d.gameSystems.players;

import java.awt.Color;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.levels.AoGLevel;
import me.hii488.handlers.LevelHandler;

public class Player {
	
	public HashMap<God, Integer> alignmentLevel = new HashMap<God, Integer>();
	private int mana;
	private int victoryPoints;
	public final Color color;
	
	public Player(Color c) {
		color = c;
	}
	
	public int getAlignmentLevel(God s) {
		if(alignmentLevel.containsKey(s)) return alignmentLevel.get(s);
		return 0;
	}
	
	public int getAlignmentLevel(String s) {
		for(God g : alignmentLevel.keySet()) if(g.name.equals(s)) return alignmentLevel.get(g);
		return 0;
	}
	
	public void setAlignmentLevel(God g, int level) {
		alignmentLevel.put(g, level);
	}
	
	public void addMana(int m) {
		setMana(getMana() + m);
	}
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
		if(LevelHandler.getCurrentLevel() != null) ((AoGLevel) LevelHandler.getCurrentLevel()).turnController.playerUpdate();
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o instanceof Player) return equals((Player) o);
		return false;
	}
	
	// Only equals on Color, to ensure Player does not change throughout a game and so can be used as a key to Maps etc.
	public boolean equals(Player p) {
		if(p == null) return false;
		if(!color.equals(p.color)) return false;
		return true;
	}
	
	public int hashCode() {
		return color.hashCode();
	}
	
}
