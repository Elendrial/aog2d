package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class BattleTortoiseUnit extends SimpleUnit{

	public BattleTortoiseUnit() {
		super();
		this.entityName = "battletortoise";
		this.maxHealth = 45;
		this.cost = 400;
		this.movementDistance = 3;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 4;
		this.eligableGod = God.GUTHIX;
	}

}