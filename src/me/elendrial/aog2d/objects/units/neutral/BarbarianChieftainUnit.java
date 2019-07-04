package me.elendrial.aog2d.objects.units.neutral;

public class BarbarianChieftainUnit extends BarbarianUnit {
	
	public BarbarianChieftainUnit() {
		super();
		this.entityName = "barbarianChieftain";
		this.maxHealth = 10;
		this.health = 10;
	}
	
	@Override
	public boolean isSummonable() {
		return false;
	}
	
}
