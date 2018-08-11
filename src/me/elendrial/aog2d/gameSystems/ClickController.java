package me.elendrial.aog2d.gameSystems;

import java.awt.event.MouseEvent;

import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.hii488.EngineSettings.Camera;
import me.hii488.dataTypes.Vector;
import me.hii488.interfaces.IInputListener;

public class ClickController implements IInputListener{
	
	private AoGLevel parentLevel;
	
	public ClickController(AoGLevel level) {
		parentLevel = level;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO: Fix this, it's not working
		((AoGTile) parentLevel.getTileGrid().getObjectAtRealPosition(new Vector(e.getX(), e.getY()).translate(Camera.currentPosition.negated()))).onClick();
	}
	
}
