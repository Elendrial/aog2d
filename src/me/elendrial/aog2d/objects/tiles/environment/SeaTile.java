package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class SeaTile extends AoGTile {

	public SeaTile() {
		this.canPassThrough = true;
		this.tileName = "sea";
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

}