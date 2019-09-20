package me.elendrial.aog2d.gameSystems;

import java.awt.event.MouseEvent;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;
import me.hii488.interfaces.IInputListener;

public class ClickController implements IInputListener{
	
	private AoGLevel parentLevel;
	public Unit selectedUnit;
	public AoGTile selectedTile;
	
	public ClickController(AoGLevel level) {
		parentLevel = level;
	}

	public boolean mouseClicked(MouseEvent e, Vector ingameLocation, boolean b) {
		// TODO: When splitting into client/server pair, change this to pass the player as the one on the client.
		// If entity on tile, onClick() that, else onClick() the tile.
		
		boolean updateSelect = false;
		// If something beforehand has already dealt with the click, we should not touch it
		if(!b){
			Player clickingPlayer = parentLevel.turnController.getCurrentPlayer();
			updateSelect = true;
			
			Unit newUnit = (Unit) parentLevel.getEntityGrid().getObjectAtRealPosition(ingameLocation);
			AoGTile newTile = (AoGTile) parentLevel.getTileGrid().getObjectAtRealPosition(ingameLocation);
			
			if(selectedUnit != null) {
				if(newUnit == null)	deselectUnit(clickingPlayer);
				else if(!selectedUnit.equals(newUnit)) deselectUnit(clickingPlayer);
				else updateSelect = false;
			}
			else {
				if(newUnit == null) deselectTile(clickingPlayer);
				else if(!newTile.equals(selectedTile)) deselectTile(clickingPlayer);
				else updateSelect = false;
			}
			
			if(updateSelect) {
				if(newUnit != null) {
					selectedUnit = newUnit; 
					selectedUnit.select(clickingPlayer);
				}
				else {
					selectedTile = newTile;
					selectedTile.select(clickingPlayer);
				}
			}
		}
		
		return updateSelect;
	}

	public void deselect(Player p) {
		deselectUnit(p);
		deselectTile(p);
	}
	
	private void deselectTile(Player p) {
		if(selectedTile != null) {
			selectedTile.deselect(p);
			selectedTile = null;
		}
	}
	
	private void deselectUnit(Player p){
		if(selectedUnit != null) {
			selectedUnit.deselect(p);
			selectedUnit = null;
		}
	}
	
}
