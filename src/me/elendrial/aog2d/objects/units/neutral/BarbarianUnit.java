package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.Unit;

public class BarbarianUnit extends Unit{

	public int[] attackRange;
	
	public BarbarianUnit() {
		super();
		this.entityName = "barbarian";
		this.maxHealth = 5;
		this.health = 5;
		this.attackRange = new int[]{1};
	}
	
	public void onSummon() {
		super.onSummon();
		if(player.getAlignmentLevel(God.NEUTRAL) == 1) player.setAlignmentLevel(God.NEUTRAL, 2);
	}
	
	public int getCaptureAmount() {
		return health;
	}

	@Override
	public void onMove() {}

	@Override
	public void onAttack(int distance) {}

	@Override
	public int getCost() {
		return 50;
	}

	@Override
	public UnitType getUnitType() {
		return UnitType.SKIRMISH;
	}

	@Override
	public boolean isSummonable() {
		return true;
	}

	@Override
	public double getMovementDistance() {
		return 6; // TODO: Check whether this lines up correctly with the different movement code
	}

	@Override
	public int[] attackRange() {
		return attackRange;
	}

	@Override
	public boolean isEligible(Player p) {
		return true;
	}

}
