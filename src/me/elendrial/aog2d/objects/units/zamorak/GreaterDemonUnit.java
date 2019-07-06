package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class GreaterDemonUnit extends SimpleUnit{

	public GreaterDemonUnit() {
		super();
		this.entityName = "greaterdemon";
		this.maxHealth = 50;
		this.health = 50;
		
		this.attackRange = new int[] {1};
		this.cost = 450;
		this.isSummonable = true;
		this.ut = UnitType.WARRIOR;
		this.movementDistance = 4;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 4;
	}

}
