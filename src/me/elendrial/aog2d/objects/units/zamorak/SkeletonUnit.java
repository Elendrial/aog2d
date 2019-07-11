package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.Unit;

public class SkeletonUnit extends Unit{

	public SkeletonUnit() {
		super();
		this.entityName = "skeleton";
		this.maxHealth = 15;
		this.health = 16; // it'll take 1 damage when summoned
		
		this.attackRange = new int[] {1};
		this.cost = 100;
		this.isSummonable = true;
		this.ut = UnitType.SKIRMISH;
		this.movementDistance = 5;
		
		this.eligableGod = God.ZAMORAK;
		this.eligableLevel = 1;
	}
	
	public void onTurnStart(Player p) {
		super.onTurnStart(p);
		
		if(p.equals(player)) {
			// Take damage if not next to a necromancer
			boolean nextToNecromancer = false;
			
			for(int i = -1; i <=1; i++) {
				for(int j =-1; j <= 1; j++) {
					if(this.parentGrid.getObjectAt(getGridPosition().translate(i, j)) instanceof NecromancerUnit) nextToNecromancer = true;
				}
			}
			
			if(!nextToNecromancer) this.damage(1);
		}
	}

}
