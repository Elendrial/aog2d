package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class HellhoundUnit extends SimpleUnit{

	public HellhoundUnit() {
		super();
		this.entityName = "hellhound";
		this.maxHealth = 30;
		this.health = 30;
		
		this.attackRange = new int[] {1};
		this.cost = 350;
		this.isSummonable = true;
		this.ut = UnitType.SKIRMISH;
		this.movementDistance = 8;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 3;
	}

}
