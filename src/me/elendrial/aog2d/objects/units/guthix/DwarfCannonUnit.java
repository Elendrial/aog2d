package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.objects.units.SimpleUnit;
import me.hii488.handlers.LevelHandler;

public class DwarfCannonUnit extends SimpleUnit{
	
	public DwarfCannonUnit() {
		super();
		this.entityName = "dwarfcannon"; // dwarfcannoncrew?
		this.maxHealth = 10;
		this.cost = 250;
		this.movementDistance = 10;
		this.ut = UnitType.WARRIOR;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableLevel = 3;
		this.eligableGod = God.GUTHIX;
	}

	public void onSpecialAction() {
		this.destroy();
		DwarfCannonCannonUnit dccu = new DwarfCannonCannonUnit();
		dccu.setGridPosition(getGridPosition());
		dccu.setPlayer(player);
		LevelHandler.getCurrentLevel().addEntity(dccu);
		dccu.onSummon();
	}
	
}