package me.elendrial.aog2d.graphics.inGame;

import java.awt.event.MouseEvent;

import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;

public class GUIAttackTileHighlight extends GUITileHighlight {

	public GUIAttackTileHighlight(Unit toMove, Vector position) {
		super(toMove, position);
		getStyle().backgroundStyle.setTextureKey("attackOverlay");
	}
	
	@Override
	public boolean onClick(MouseEvent e) {
		callbackUnit.attack(gridPos);
		return true;
	}

}
