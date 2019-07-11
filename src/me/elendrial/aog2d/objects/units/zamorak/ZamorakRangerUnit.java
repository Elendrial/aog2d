package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class ZamorakRangerUnit extends Unit{

	public ZamorakRangerUnit() {
		super();
		this.entityName = "zamorakranger";
		this.maxHealth = 20;
		this.health = 20;
		
		this.attackRange = new int[] {2,3};
		this.cost = 200;
		this.isSummonable = true;
		this.ut = UnitType.RANGER;
		this.movementDistance = 6;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 2;
	}

}
