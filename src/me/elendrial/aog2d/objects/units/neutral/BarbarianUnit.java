package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.Unit;

public class BarbarianUnit extends Unit{
	
	public BarbarianUnit() {
		super();
		this.entityName = "barbarian";
		this.maxHealth = 5;
		this.health = 5;
		this.attackRange = new int[]{1};
		
		this.eligableGod = God.NEUTRAL;
		this.eligableLevel = 1;
	}
	
	public int getCaptureAmount() {
		return health;
	}
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
	public boolean isEligible(Player p) {
		return true;
	}

}
