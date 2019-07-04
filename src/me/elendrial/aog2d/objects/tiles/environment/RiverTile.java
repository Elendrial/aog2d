package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class RiverTile extends AoGTile {

	public RiverTile() {
		this.canPassThrough = true;
		this.tileName = "river";
	}

	@Override
	public double movementCost(Unit unit) {
		switch(unit.getUnitType()) {
		case FLYING:
			return 1;
		case SKIRMISH:
			return 3;
		case TITAN:
			return 1;
		default:
			return 1000000; // Just some ridiculously large number to act as something you can't pass through.
		}
	}
	
	@Override
	public float defenceBonus(Unit u) {
		return 0.1f;
	}

}
