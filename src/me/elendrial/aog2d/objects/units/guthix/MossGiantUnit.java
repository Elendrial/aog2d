package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.Unit;

public class MossGiantUnit extends Unit{

	public MossGiantUnit() {
		super();
		this.entityName = "mossgiant";
		this.maxHealth = 40;
		this.cost = 400;
		this.movementDistance = 4;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 4;
		this.eligableGod = God.GUTHIX;
	}

}