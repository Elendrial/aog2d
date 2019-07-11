package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class JadeVineUnit extends Unit{

	public JadeVineUnit() {
		super();
		this.entityName = "jadevine";
		this.maxHealth = 5;
		this.cost = 100;
		this.movementDistance = 1;
		this.ut = UnitType.CREEPER;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 4;
		this.eligableGod = God.GUTHIX;
	}

}