package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class SaradominOwlUnit extends SimpleUnit{

	public SaradominOwlUnit() {
		super();
		this.entityName = "saradominowl";
		this.maxHealth = 5;
		this.cost = 100;
		this.movementDistance = 6;
		this.ut = UnitType.FLYING;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 1;
	}

}
