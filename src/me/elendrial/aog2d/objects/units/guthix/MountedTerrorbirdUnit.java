package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class MountedTerrorbirdUnit extends Unit{

	public MountedTerrorbirdUnit() {
		super();
		this.entityName = "mountedterrorbird";
		this.maxHealth = 15;
		this.cost = 200;
		this.movementDistance = 10;
		this.ut = UnitType.SKIRMISH;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 2;
		this.eligableGod = God.GUTHIX;
	}

}