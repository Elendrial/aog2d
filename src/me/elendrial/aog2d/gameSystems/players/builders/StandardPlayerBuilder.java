package me.elendrial.aog2d.gameSystems.players.builders;

import java.awt.Color;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;

public class StandardPlayerBuilder implements PlayerBuilder {

	private God[] playerGods = new God[] {God.SARADOMIN, God.ZAMORAK, God.GUTHIX};
	private Color color = Color.RED;
	
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
		
		p.setMana(100);
		p.setVictoryPoints(0);
		p.alignmentLevel.put(God.NEUTRAL, 0); //TODO: Check whether these should start at 1 or 0.
		
		for(God s : playerGods) p.alignmentLevel.put(s, 0);
		
		return p;
	}

}
