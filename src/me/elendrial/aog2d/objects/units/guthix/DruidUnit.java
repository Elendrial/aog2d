package me.elendrial.aog2d.objects.units.guthix;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.SimpleUnit;
import me.elendrial.aog2d.objects.units.Unit;

public class DruidUnit extends SimpleUnit{

	public DruidUnit() {
		super();
		this.entityName = "druid";
		this.maxHealth = 10;
		this.cost = 150;
		this.movementDistance = 6;
		this.ut = UnitType.HELPER;
		this.attackRange = new int[] {1};
		
		this.isSummonable = true;
		this.health = this.maxHealth;
		
		this.eligableGod = God.SARADOMIN;
		this.eligableLevel = 4;
	}
	
	public void onTurnStart(Player p) {
		super.onTurnStart(p);

		for(int i = -1; i <=1; i++) {
			for(int j =-1; j <= 1; j++) {
				Unit u = (Unit) this.getParentGrid().getObjectAt(getGridPosition().translate(i, j));
				
				if(u != null) 
					u.buff(5);
				
			}
		}
	}
}