package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class ForestTile extends AoGTile {

	public ForestTile() {
		this.canPassThrough = true;
		this.tileName = "forest";
	}

	@Override
	public double movementCost(Unit unit) {
		if(unit.getUnitType() == UnitType.FLYING) return 1;
		if(unit.getUnitType() == UnitType.TITAN) return 1;
		if(God.isUnitAligned(unit, God.GUTHIX)) return 1;
		
		return 2;
	}
	
	@Override
	public float defenceBonus(Unit u) {
		return God.isUnitAligned(u, God.GUTHIX) ? 0.3f : 0.2f;
	}
	
}
