package me.elendrial.aog2d.graphics.inGame;

import java.awt.event.MouseEvent;

import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.premadeTypes.GUIStandardBox;

public class GUITileHighlight extends GUIStandardBox{
	Unit callbackUnit;
	
	public GUITileHighlight(Unit toMove, Vector position) {
		super(AoGStyleGroup.getInstance().getStyle("tileOverlay"));
		this.setPosition(position);
		callbackUnit = toMove;
		addTag("tileHighlight");
	}
	
	@Override
	public boolean onClick(MouseEvent e) {
		return true;
	}
	
}