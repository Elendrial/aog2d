package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.objects.units.Unit;

public class VillageTile extends StructureTile{
	
	public VillageTile() {
		this.canPassThrough = true;
		this.tileName = "village";
		
		this.captureTime = 10;
		this.manaPerTurn = 25;
	}
	
	public void onLoad() {
		super.onLoad();
	}

	@Override
	public double movementCost(Unit unit) {
		return 1;
	}
	
}
