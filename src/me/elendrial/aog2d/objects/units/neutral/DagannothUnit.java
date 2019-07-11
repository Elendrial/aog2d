package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class DagannothUnit extends Unit{

	// TODO: amphibious, able to attack flying units
	public DagannothUnit() {
		super();
		this.entityName = "dagannoth";
		this.maxHealth = 30;
		this.health = 30;
		
		this.attackRange = new int[] {1};
		this.cost = 400;
		this.isSummonable = true;
		this.ut = UnitType.WARRIOR;
		this.movementDistance = 6;
		
		this.eligableGod = God.NEUTRAL;
		this.eligableLevel = 4;
	}

}
