package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.BarbarianUnit;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.handlers.LevelHandler;

public abstract class StructureTile extends AoGTile implements IUpdating {

	protected int captureTime = 0;
	protected int captureAmount;
	protected int manaPerTurn;
	public Player capturedBy;
	public Player progressingCapturer;
	
	public StructureTile() {
		super();
	}
	
	public void onPlace() {
		this.registerAsUpdating((AoGLevel) getParentGrid().getParentLevel());
	}
	
	public void onTurnStart(Player playerTurn) {
		boolean suppressed = false;			
		GridEntity ge = LevelHandler.getCurrentLevel().getEntityGrid().getObjectAt(getGridPosition());
		
		if(ge != null) {
			if(ge instanceof BarbarianUnit) {
				BarbarianUnit u = (BarbarianUnit) ge;
				
				// Enemy barbarian capturing
				if(!u.getPlayer().equals(capturedBy)) {
					if(!u.getPlayer().equals(progressingCapturer))	progressingCapturer = u.getPlayer();
					captureAmount += u.getHealth();
					suppressed = true;
				}
				// Friendly barbarian getting rid of enemy progress
				else {
					int change = u.getHealth();
					captureAmount -= captureAmount < change ? captureAmount : change;
				}
				
			}
			
			// Enemy unit suppressing the building
			else if(((Unit) ge).getPlayer() != capturedBy) {
				suppressed = true;
			}
			
			// Friendly unit getting rid of enemy progress
			else {
				if(captureAmount > 0) {
					float change = ((float) ((Unit) ge).getHealth()) * 0.75f;
					captureAmount -= captureAmount < change ? captureAmount : change;
				}
			}
		}
		// Unattended progress slowly decaying
		else if(captureAmount > 0){
			float change = (((float) captureAmount) * 0.25f);
			captureAmount -= change < 2 ? 2 : (int) change;
		}
		
		
		// Enemy captures structure
		if(captureAmount >= captureTime) {
			capturedBy = progressingCapturer;
			captureAmount = 0;
		}
		
		
		// Mana gain
		if(capturedBy != null && capturedBy.equals(playerTurn)) {
			capturedBy.mana += (int) ((float) manaPerTurn * (suppressed ? 0.75f : 1f));
		}
		
	}

}
