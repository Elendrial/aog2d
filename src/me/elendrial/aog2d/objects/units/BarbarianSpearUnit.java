package me.elendrial.aog2d.objects.units;

public class BarbarianSpearUnit extends BarbarianUnit {
	
	public BarbarianSpearUnit() {
		super();
		this.entityName = "barbarianSpear";
		this.maxHealth = 7;
		this.health = 7;
		this.attackRange = new int[]{2,3};
		
	}
	
	@Override
	public String getTextureLocation() {
		return "textures/units/barbarianSpear.png";
	}
	
}
