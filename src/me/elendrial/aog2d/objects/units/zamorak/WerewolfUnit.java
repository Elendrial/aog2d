package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class WerewolfUnit extends SimpleUnit{

	public WerewolfUnit() {
		super();
		this.entityName = "werewolf";
		this.maxHealth = 10;
		this.health = 10;
		
		this.attackRange = new int[] {1};
		this.cost = 100;
		this.isSummonable = true;
		this.ut = UnitType.SKIRMISH;
		this.movementDistance = 6;
	}

}
