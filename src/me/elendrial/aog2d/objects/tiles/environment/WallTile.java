package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.Unit;

public class WallTile extends AoGTile {

	// L->R, U->D, L->U, U->R, L->D, D->R
	public int direction = 0;
	
	public WallTile() {
		this.canPassThrough = true;
		this.tileName = "walls/wall";
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
		return 0.1f;
	}
	
	@Override
	public int getHighestState() {
		return 5; // Horizontal, Vertical, L->U, U->R, L->D, D->R   - NB: I don't think this is like size()
	}
	
	@Override
	public int getTextureState() {
		return direction;
	}
	
	public void setDirection(int d) {
		direction = d;
	}
	
}
