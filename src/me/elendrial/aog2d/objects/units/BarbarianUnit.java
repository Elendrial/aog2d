package me.elendrial.aog2d.objects.units;

public class BarbarianUnit extends Unit{

	public BarbarianUnit() {
		super();
		this.entityName = "barbarian";
	}
	
	@Override
	public String getTextureLocation() {
		return "textures/units/barbarian.png";
	}

	@Override
	public int getTextureState() {
		return 0;
	}

	@Override
	public int getHighestState() {
		return 0;
	}

}
