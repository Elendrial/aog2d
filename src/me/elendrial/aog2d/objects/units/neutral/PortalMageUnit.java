package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.objects.units.Unit;

public class PortalMageUnit extends Unit{
	
	public PortalMageUnit() {
		super();
		this.entityName = "portalmage";
		this.maxHealth = 5;
		this.health = 5;
		
		this.attackRange = new int[] {1};
		this.cost = 200;
		this.isSummonable = true;
		this.ut = UnitType.HELPER;
		this.movementDistance = 7;
		
	}
	
	public void onSpecialAction() {
		this.destroy();
		// TODO: Place down a special portal that only summons from one god.
		// Maybe this means refactoring the gui code, to something like getSummonGUI(God... g)
	}
	
}
