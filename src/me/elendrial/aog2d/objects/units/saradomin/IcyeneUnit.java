package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class IcyeneUnit extends SimpleUnit{

	public IcyeneUnit() {
		super();
		this.entityName = "icyene";
		this.maxHealth = 40;
		this.cost = 800;
		this.movementDistance = 8;
		this.ut = UnitType.FLYING;
		this.attackRange = new int[] {1,2,3};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 5;
	}

}