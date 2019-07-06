package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.objects.units.SimpleUnit;

public class DwarfCannonCannonUnit extends SimpleUnit{

	public DwarfCannonCannonUnit() {
		super();
		this.entityName = "dwarfcannoncannon";
		this.maxHealth = 20;
		this.cost = 0;
		this.movementDistance = 0;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {2,3,4,5,6,7};
		
		this.isSummonable = false;
		this.health = this.maxHealth;
	}
	
}
