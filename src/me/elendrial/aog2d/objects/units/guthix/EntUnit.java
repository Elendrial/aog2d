package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.tiles.environment.ForestTile;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class EntUnit extends SimpleUnit{

	public EntUnit() {
		super();
		this.entityName = "ent";
		this.maxHealth = 45;
		this.cost = 700;
		this.movementDistance = 4;
		this.ut = UnitType.CREEPER;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 5;
		this.eligableGod = God.GUTHIX;
	}
	
	public void onMove() {
		// Grow forest
		
		AoGTile t = (AoGTile) getParentLevel().getTileGrid().getObjectAt(getGridPosition());
		if(t instanceof OpenTile) {
			getParentLevel().getTileGrid().setObjectAt(getGridPosition(), new ForestTile());
		}
	}


}