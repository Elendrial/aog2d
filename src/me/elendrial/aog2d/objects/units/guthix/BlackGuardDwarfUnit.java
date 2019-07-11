package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class BlackGuardDwarfUnit extends Unit{

	public BlackGuardDwarfUnit() {
		super();
		this.entityName = "blackguarddwarf";
		this.maxHealth = 20;
		this.cost = 175;
		this.movementDistance = 4;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 2;
		this.eligableGod = God.GUTHIX;
	}

}