package me.elendrial.aog2d.objects.units;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;

// This could probably be merged up with Unit, easier to not do that right now though.
// NB: while this is "Simple", in reality it's just as overridable as Unit is in itself, so really it might be worth having literally every unit extend this
public abstract class SimpleUnit extends Unit{
	
	public int[] attackRange;
	public boolean isSummonable;
	public int cost;
	public UnitType ut;
	public int movementDistance;
	
	public int eligableLevel;
	public God eligableGod;
	
	@Override
	public void onMove() {}

	@Override
	public void onAttack(int distance) {}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public UnitType getUnitType() {
		return ut;
	}

	@Override
	public double getMovementDistance() {
		return movementDistance;
	}

	@Override
	public boolean isSummonable() {
		return isSummonable;
	}

	@Override
	public int[] attackRange() {
		return attackRange;
	}
	
	@Override
	public boolean isEligible(Player p) {
		if(eligableGod == null) return true;
		return p.getAlignmentLevel(eligableGod) >= eligableLevel;
	}
	
}
