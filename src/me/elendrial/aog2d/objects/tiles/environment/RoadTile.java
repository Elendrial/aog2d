package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class RoadTile extends AoGTile{
	
	public RoadTile() {
		this.canPassThrough = true;
		this.tileName = "road";
	}
	
	@Override
	public double movementCost(Unit unit) {
		switch(unit.getUnitClass()) {
		case FLYING:
			return 1;
		case TITAN:
			return 1;
		default:
			return 0.75;
		}
	}

}
