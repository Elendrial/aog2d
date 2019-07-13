package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.tiles.structure.StructureTile;

public class BarbarianChieftainUnit extends BarbarianUnit {
	
	public BarbarianChieftainUnit() {
		super();
		this.entityName = "barbarianChieftain";
		this.maxHealth = 10;
		this.health = 10;
	}
	
	public void onSummon() {
		AoGTile standingOn = (AoGTile) this.parentLevel.getTileGrid().getObjectAt(getGridPosition());
		if(standingOn instanceof StructureTile) {
			((StructureTile) standingOn).capturedBy = this.player;
		}
		super.onSummon();
	}
	
	@Override
	public boolean isSummonable() {
		return false;
	}
	
}
