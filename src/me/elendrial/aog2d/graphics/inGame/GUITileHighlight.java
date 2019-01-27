package me.elendrial.aog2d.graphics.inGame;

import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.premadeTypes.GUIStandardBox;

public class GUITileHighlight extends GUIStandardBox{
	Unit callbackUnit;
	Vector gridPos;
	
	public GUITileHighlight(Unit toMove, Vector position) {
		super(AoGStyleGroup.getInstance().getStyle("tileOverlay"));
		this.setPosition(position.getLocation().scale(toMove.getGrid().getGridScale()));
		gridPos = position;
		callbackUnit = toMove;
		addTag("tileHighlight");
	}
	
}