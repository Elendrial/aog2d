package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.objects.units.Unit;

public class TowerTile extends StructureTile {
	
	public TowerTile() {
		this.canPassThrough = true;
		this.tileName = "tower";
		
		this.captureTime = 15;
		this.manaPerTurn = 75;
	}
	
	public void onLoad() {
		super.onLoad();
	}

	@Override
	public double movementCost(Unit unit) {
		return 1;
	}
	
}
