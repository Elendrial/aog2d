package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class VoidKnightUnit extends SimpleUnit{

	public VoidKnightUnit() {
		super();
		this.entityName = "voidknight";
		this.maxHealth = 25;
		this.cost = 250;
		this.movementDistance = 6;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 2;
		this.eligableGod = God.GUTHIX;
	}

}