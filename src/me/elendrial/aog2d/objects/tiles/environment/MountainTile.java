package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class MountainTile extends AoGTile {

	public MountainTile() {
		this.canPassThrough = true;
		this.tileName = "mountain";
	}

	@Override
	public double movementCost(Unit unit) {
		switch(unit.getUnitType()) {
		case FLYING:
			return 1;
		default:
			return 1000000; // Just some ridiculously large number to act as something you can't pass through.
		}
	}
	
	@Override
	public float defenceBonus(Unit u) {
		return 0.4f;
	}
	
}
