package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.units.Unit;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;

public class SeaSlugUnit extends Unit{

	// TODO: Make this amphibious
	public SeaSlugUnit() {
		super();
		this.entityName = "seaslug";
		this.maxHealth = 10;
		this.health = 10;
		
		this.attackRange = new int[] {1};
		this.cost = 100;
		this.isSummonable = true;
		this.ut = UnitType.SKIRMISH;
		this.movementDistance = 3;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 3;
	}
	
	public void attack(Vector v) {
		((AoGLevel) parentLevel).tileOverlayGUISet.empty();
		((AoGLevel) this.parentLevel).clickController.deselect(player);
		
		Unit attacked = (Unit) parentGrid.getObjectAt(v);
		attacked.setPlayer(getPlayer());
		this.destroy();
	}
	
	public boolean canAttackUnit(Unit u) {
		return u.getHealth() <= this.getHealth();
	}

}
