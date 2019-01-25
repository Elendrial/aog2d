package me.elendrial.aog2d.objects.units;

public class BarbarianUnit extends Unit{

	public static int[] attackRange = {1};
	
	public BarbarianUnit() {
		super();
		this.entityName = "barbarian";
		this.maxHealth = 5;
		this.health = 5;
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

	@Override
	public void onStartTurn() {
		// Increase capture of buildings.
	}
	
	public int getCaptureAmount() {
		return health;
	}

	@Override
	public void onMove() {}

	@Override
	public void onAttack(int distance) {}

	@Override
	public void onEndTurn() {}

	@Override
	public int getCost() {
		return 50;
	}

	@Override
	public UnitClass getUnitClass() {
		return UnitClass.SKIRMISH;
	}

	@Override
	public boolean isSummonable() {
		return true;
	}

	@Override
	public double getMovementDistance() {
		return 5; // TODO: Set this number properly
	}

	@Override
	public int[] attackRange() {
		return attackRange;
	}

}
