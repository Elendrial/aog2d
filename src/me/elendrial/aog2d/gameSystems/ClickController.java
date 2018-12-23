package me.elendrial.aog2d.gameSystems;

import java.awt.event.MouseEvent;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.Camera;
import me.hii488.interfaces.IInputListener;

public class ClickController implements IInputListener{
	
	private AoGLevel parentLevel;
	public Unit selectedUnit;
	public AoGTile selectedTile;
	
	public ClickController(AoGLevel level) {
		parentLevel = level;
	}

	public boolean mouseClicked(MouseEvent e, boolean b) {
		// TODO: When splitting into client/server pair, change this to pass the player as the one on the client.
		// If entity on tile, onClick() that, else onClick() the tile.
		
		// If something beforehand as already dealt with the click, we should not touch it
		if(!b){
			System.out.println("in clickcontroller logic");
			Vector clickPosition = new Vector(e.getX(), e.getY()).translate(Camera.getPosition().negated());
			Player clickingPlayer = parentLevel.turnController.getCurrentPlayer();
			
			deselectAll(clickingPlayer);
			
			if(parentLevel.getEntityGrid().getObjectAtRealPosition(e.getX(), e.getY()) != null) {
				selectedUnit = (Unit) parentLevel.getEntityGrid().getObjectAtRealPosition(clickPosition); // I know that all entities will be Units
				selectedUnit.select(clickingPlayer);
			}
			else {
				selectedTile = (AoGTile) parentLevel.getTileGrid().getObjectAtRealPosition(clickPosition);
				selectedTile.select(clickingPlayer);
			}
		}
		return false;
	}

	public void deselectAll(Player p) {
		if(selectedUnit != null) {
			selectedUnit.deselect(p);
			selectedUnit = null;
		}
		if(selectedTile != null) {
			selectedTile.deselect(p);
			selectedTile = null;
		}
	}
	
}
