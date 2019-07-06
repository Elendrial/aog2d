package me.elendrial.aog2d.objects.units.zamorak;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.units.SimpleUnit;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.handlers.LevelHandler;

public class NecromancerUnit extends SimpleUnit{

	public NecromancerUnit() {
		super();
		this.entityName = "necromancer";
		this.maxHealth = 10;
		this.health = 10;
		
		this.attackRange = new int[] {1};
		this.cost = 150;
		this.isSummonable = true;
		this.ut = UnitType.HELPER;
		this.movementDistance = 5;
		
	}
	
	public void onTurnStart(Player p) {
		super.onTurnStart(p);
		
		for(int i = -1; i <=1; i++) {
			for(int j =-1; j <= 1; j++) {
				GridEntity u = this.getParentGrid().getObjectAt(getGridPosition().translate(i, j));

				// Fully heal any adjacent Skeletons
				if(u != null) {
					if(u instanceof SkeletonUnit) ((SkeletonUnit) u).heal(((SkeletonUnit) u).maxHealth - ((SkeletonUnit) u).health);
				}
				
				// Summon a skeleton if next to a bone pile, and remove the bone pile
				else {
					AoGTile t = (AoGTile) this.getParentLevel().getTileGrid().getObjectAt(getGridPosition().translate(i,j));
					if(t.containsBones()) {
						SkeletonUnit skel = new SkeletonUnit();
						skel.setGridPosition(getGridPosition().translate(i,j));
						skel.setPlayer(p);
						LevelHandler.getCurrentLevel().addEntity(skel);
						skel.onSummon();
						
						t.setContainsBones(false);
					}
				}
				
			}
		}
	}
	
}
