package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.tiles.environment.ForestTile;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.units.Unit;

public class PyrelordUnit extends Unit{

	public PyrelordUnit() {
		super();
		this.entityName = "gargoyle";
		this.maxHealth = 50;
		this.health = 50;
		
		this.attackRange = new int[] {1};
		this.cost = 750;
		this.isSummonable = true;
		this.ut = UnitType.TITAN;
		this.movementDistance = 3;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 5;
	}
	
	public void onMove() {
		// Burn down forest
		
		AoGTile t = (AoGTile) getParentLevel().getTileGrid().getObjectAt(getGridPosition());
		if(t instanceof ForestTile) {
			getParentLevel().getTileGrid().setObjectAt(getGridPosition(), new OpenTile());
		}
	}

}
