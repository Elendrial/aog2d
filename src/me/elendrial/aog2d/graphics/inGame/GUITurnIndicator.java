package me.elendrial.aog2d.graphics.inGame;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.premadeTypes.GUIStandardBox;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUITurnIndicator extends GUIStandardBox implements IUpdating{

	public GUITurnIndicator(GUIStyle s) {
		super(s);
		s.textStyle.setHorizontalJustification(-1);
		this.setDimensions(new Vector(100, 30));
		this.registerAsUpdating();

		this.setText("Player Turn");
	}
	
	public void onTurnStart(Player playerTurn) {
		this.style.backgroundStyle.setBackgroundColor(playerTurn.color);
	}
	
	public void turnInit(Player playerTurn) {
		this.style.backgroundStyle.setBackgroundColor(playerTurn.color);
	}
	
}
