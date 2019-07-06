package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class PaladinUnit extends SimpleUnit{

	public PaladinUnit() {
		super();
		this.entityName = "paladin";
		this.maxHealth = 10;
		this.cost = 100;
		this.movementDistance = 6;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
	}

}
