package me.elendrial.aog2d.graphics.inGame;

import java.awt.event.MouseEvent;

import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;

public class GUIMoveTileHighlight extends GUITileHighlight{
	private final double distance;
	
	public GUIMoveTileHighlight(Unit toMove, Vector position, double distance) {
		super(toMove, position);
		getStyle().backgroundStyle.setTextureKey("moveOverlay");
		this.distance = distance;
	}
	
	@Override
	public boolean onClick(MouseEvent e) {
		callbackUnit.move(gridPos, distance);
		return true;
	}

}
