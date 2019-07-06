package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class GorillaUnit extends SimpleUnit{

	public GorillaUnit() {
		super();
		this.entityName = "gorilla";
		this.maxHealth = 20;
		this.health = 20;
		
		this.attackRange = new int[] {1};
		this.cost = 250;
		this.isSummonable = true;
		this.ut = UnitType.WARRIOR;
		this.movementDistance = 4;
		
		this.eligableGod = God.NEUTRAL;
		this.eligableLevel = 1;
	}

}
