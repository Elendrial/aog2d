package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class UnicornUnit extends SimpleUnit{

	public UnicornUnit() {
		super();
		this.entityName = "unicorn";
		this.maxHealth = 30;
		this.cost = 350;
		this.movementDistance = 8;
		this.ut = UnitType.SKIRMISH;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 3;
	}

}