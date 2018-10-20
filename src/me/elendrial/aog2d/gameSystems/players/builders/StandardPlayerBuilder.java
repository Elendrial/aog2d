package me.elendrial.aog2d.gameSystems.players.builders;

import java.awt.Color;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;

public class StandardPlayerBuilder implements PlayerBuilder {

	private God[] playerGods = new God[] {God.SARADOMIN, God.ZAMORAK, God.GUTHIX};
	private Color color;
	
	public StandardPlayerBuilder setGods(God[] gods) {
		// TODO: Handle this better
		if(gods.length != 3) throw new RuntimeException("Must pass an array of length 3 to setGods(), do not include NEUTRAL.");
		playerGods = gods;
		return this;
	}
	
	public StandardPlayerBuilder setColor(Color c) {
		color = c;
		return this;
	}
	
	@Override
	public Player build() {
		Player p = new Player(color);
		
		p.mana = 100;
		p.victoryPoints = 0;
		p.alignmentLevel.put(God.NEUTRAL, 1);
		
		for(God s : playerGods) p.alignmentLevel.put(s, 0);
		
		return p;
	}

}
