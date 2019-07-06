package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class AviansieUnit extends SimpleUnit{

	public AviansieUnit() {
		super();
		this.entityName = "aviansie";
		this.maxHealth = 20;
		this.health = 20;
		
		this.attackRange = new int[] {1};
		this.cost = 400;
		this.isSummonable = true;
		this.ut = UnitType.FLYING;
		this.movementDistance = 6;
		
		this.eligableGod = God.NEUTRAL;
		this.eligableLevel = 1;
	}

}
