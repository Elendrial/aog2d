package me.elendrial.aog2d.graphics.inGame;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.premadeTypes.GUIStandardBox;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUIManaIndicator extends GUIStandardBox implements IUpdating {

	public GUIManaIndicator(GUIStyle s) {
		super(s);
		this.setDimensions(new Vector(100, 30));
		this.registerAsUpdating();
	}
	
	public void onTurnStart(Player playerTurn) {
		this.setText("Mana: " + playerTurn.getMana());
	}
	
	public void turnInit(Player playerTurn) {
		this.setText("Mana: " + playerTurn.getMana());
	}
	
	public void onPlayerUpdate(Player p) {
		this.setText("Mana: " + p.getMana());
	}
	
}
