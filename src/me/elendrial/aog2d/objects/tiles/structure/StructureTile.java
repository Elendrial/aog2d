package me.elendrial.aog2d.objects.tiles.structure;

import java.awt.Color;
import java.awt.Graphics;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;
import me.elendrial.aog2d.objects.units.neutral.BarbarianUnit;
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
		Unit ge = (Unit) LevelHandler.getCurrentLevel().getEntityGrid().getObjectAt(getGridPosition());
		
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
			else if(ge.getPlayer() != capturedBy) {
				suppressed = true;
			}
			
			// Friendly unit getting rid of enemy progress
			else {
				if(captureAmount > 0) {
					float change = ((float) ge.getHealth()) * 0.75f;
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
			capturedBy.addMana((int) ((float) manaPerTurn * (suppressed ? 0.75f : 1f)));
		}
		
	}
	
	public void turnInit(Player p) {}
	
	public void render(Graphics g) {
		super.render(g);
		
		///// Temporary way of showing which side the structure is on
		if(capturedBy != null) {
			Color c = g.getColor();
			g.setColor(capturedBy.color);
			g.drawLine(getPosition().getIX(), getPosition().getIY() + getTextureHeight()-1, getPosition().getIX() + getTextureWidth(), getPosition().getIY() + getTextureHeight()-1);
			g.setColor(c);
		}
	}

}
