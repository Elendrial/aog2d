package me.elendrial.aog2d.objects.units.saradomin;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class SaradominRangerUnit extends SimpleUnit{

	public SaradominRangerUnit() {
		super();
		this.entityName = "saradominranger";
		this.maxHealth = 20;
		this.cost = 200;
		this.movementDistance = 6;
		this.ut = UnitType.RANGER;
		this.attackRange = new int[] {2,3};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 2;
	}

}