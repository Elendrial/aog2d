package me.elendrial.aog2d.objects.units;

import me.elendrial.aog2d.gameSystems.players.Player;

public class BarbarianUnit extends Unit{

	public int[] attackRange;
	
	public BarbarianUnit() {
		super();
		this.entityName = "barbarian";
		this.maxHealth = 5;
		this.health = 5;
		this.attackRange = new int[]{1};
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
	public void onTurnStart(Player p) {
		super.onTurnStart(p);
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
