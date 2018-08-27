package me.elendrial.aog2d.graphics.inGame;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;

public class AoGGuiElements {
	// Create standard gui elements here.
	
	public GUIOptionBox getSummonGUI(Player p) {
		GUIOptionBox box = new GUIOptionBox(AoGStyleGroup.getInstance().styles.get("summonMenu"));
		
		return box;
	}
}
