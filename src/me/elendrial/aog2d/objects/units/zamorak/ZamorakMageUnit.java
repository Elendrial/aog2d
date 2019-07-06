package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class ZamorakMageUnit extends SimpleUnit{

	public ZamorakMageUnit() {
		super();
		this.entityName = "zamorakmage";
		this.maxHealth = 20;
		this.health = 20;
		
		this.attackRange = new int[] {3,4,5};
		this.cost = 250;
		this.isSummonable = true;
		this.ut = UnitType.MAGE;
		this.movementDistance = 3;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 3;
	}

}
