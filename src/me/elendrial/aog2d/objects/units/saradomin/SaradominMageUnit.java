package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class SaradominMageUnit extends Unit{

	public SaradominMageUnit() {
		super();
		this.entityName = "saradominmage";
		this.maxHealth = 20;
		this.cost = 250;
		this.movementDistance = 3;
		this.ut = UnitType.MAGE;
		this.attackRange = new int[] {3,4,5};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 3;
	}

}