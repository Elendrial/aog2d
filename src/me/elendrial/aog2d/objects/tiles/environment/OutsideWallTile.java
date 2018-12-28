package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class OutsideWallTile extends AoGTile{
	
	public OutsideWallTile() {
		this.canPassThrough = false;
		this.tileName = "outsideWall";
	}

	@Override
	public double movementCost(Unit unit) {
		return -1;
	}
	
}
