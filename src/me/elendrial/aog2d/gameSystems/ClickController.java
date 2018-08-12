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
		// TODO: When splitting into client/server pair, change this to pass the player as the one on the client.
		// If entity on tile, onClick() that, else onClick() the tile.
		
		((AoGTile) parentLevel.getTileGrid().getObjectAtRealPosition(new Vector(e.getX(), e.getY()).translate(Camera.currentPosition.negated()))).onClick(parentLevel.turnController.getCurrentPlayer());
	}
	
}
