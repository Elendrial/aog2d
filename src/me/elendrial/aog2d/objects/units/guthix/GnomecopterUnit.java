package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class GnomecopterUnit extends SimpleUnit{

	public GnomecopterUnit() {
		super();
		this.entityName = "gnomecopter";
		this.maxHealth = 20;
		this.cost = 450;
		this.movementDistance = 10;
		this.ut = UnitType.FLYING;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 3;
		this.eligableGod = God.GUTHIX;
	}

}