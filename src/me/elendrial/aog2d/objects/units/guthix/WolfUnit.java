package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class WolfUnit extends SimpleUnit{

	public WolfUnit() {
		super();
		this.entityName = "wolf";
		this.maxHealth = 10;
		this.cost = 150;
		this.movementDistance = 8;
		this.ut = UnitType.SKIRMISH;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
	}

}