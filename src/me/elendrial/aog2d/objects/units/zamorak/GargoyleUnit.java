package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class GargoyleUnit extends Unit{

	public GargoyleUnit() {
		super();
		this.entityName = "gargoyle";
		this.maxHealth = 30;
		this.health = 30;
		
		this.attackRange = new int[] {1,2,3};
		this.cost = 600;
		this.isSummonable = true;
		this.ut = UnitType.FLYING;
		this.movementDistance = 6;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 4;
	}

}
