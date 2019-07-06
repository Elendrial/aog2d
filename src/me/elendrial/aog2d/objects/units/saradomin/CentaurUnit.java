package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class CentaurUnit extends SimpleUnit{

	public CentaurUnit() {
		super();
		this.entityName = "centaur";
		this.maxHealth = 25;
		this.cost = 300;
		this.movementDistance = 9;
		this.ut = UnitType.RANGER;
		this.attackRange = new int[] {1,2,3};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 4;
	}

}