package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class WhiteKnightUnit extends SimpleUnit{

	public WhiteKnightUnit() {
		super();
		this.entityName = "whiteknight";
		this.maxHealth = 25;
		this.cost = 250;
		this.movementDistance = 5;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 2;
	}

}