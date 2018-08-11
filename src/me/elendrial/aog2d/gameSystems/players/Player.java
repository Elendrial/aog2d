package me.elendrial.aog2d.gameSystems.players;

import java.awt.Color;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.gods.God;

public class Player {
	
	public HashMap<God, Integer> alignmentLevel = new HashMap<God, Integer>();
	public int mana;
	public int victoryPoints;
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
	
	public boolean equals(Player p) {
		if(!color.equals(p.color)) return false;
		if(victoryPoints != p.victoryPoints) return false;
		if(mana != p.mana) return false;
		return true;
	}
	
}
