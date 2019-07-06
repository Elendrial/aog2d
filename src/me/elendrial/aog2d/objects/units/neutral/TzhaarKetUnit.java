package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class TzhaarKetUnit extends SimpleUnit{

	public TzhaarKetUnit() {
		super();
		this.entityName = "tzhaarket";
		this.maxHealth = 35;
		this.health = 35;
		
		this.attackRange = new int[] {1};
		this.cost = 300;
		this.isSummonable = true;
		this.ut = UnitType.WARRIOR;
		this.movementDistance = 4;
		
		this.eligableGod = God.NEUTRAL;
		this.eligableLevel = 4;
	}

}
