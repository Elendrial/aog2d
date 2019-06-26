package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.objects.units.Unit;

public class TempleTile extends StructureTile {

	public TempleTile() {
		this.canPassThrough = true;
		this.tileName = "temple";
		
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
