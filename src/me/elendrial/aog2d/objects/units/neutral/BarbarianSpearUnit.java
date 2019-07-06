package me.elendrial.aog2d.objects.units.neutral;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;

public class BarbarianSpearUnit extends BarbarianUnit {
	
	public BarbarianSpearUnit() {
		super();
		this.entityName = "barbarianSpear";
		this.maxHealth = 7;
		this.health = 7;
		this.attackRange = new int[]{2,3};
		
	}
	
	public int getCost() {
		return 125;
	}
	
	@Override
	public String getTextureLocation() {
		return "textures/units/barbarianSpear.png";
	}
	
	@Override
	public boolean isEligible(Player p) {
		return p.getAlignmentLevel(God.NEUTRAL) >= 3;
	}
	
}
