package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class BlackKnightUnit extends SimpleUnit{

	public BlackKnightUnit() {
		super();
		this.entityName = "blackknight";
		this.maxHealth = 25;
		this.health = 25;
		
		this.attackRange = new int[] {1};
		this.cost = 250;
		this.isSummonable = true;
		this.ut = UnitType.WARRIOR;
		this.movementDistance = 5;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 2;
	}

}
