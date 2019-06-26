package me.elendrial.aog2d.objects.tiles;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.gameObjects.tiles.BaseTile;

public abstract class AoGTile extends BaseTile {
	
	private boolean containsBones;
	
	public abstract double movementCost(Unit unit);
	public float defenceBonus(Unit u) {return 0f;}
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public void select(Player p) {}  // Show some basic information
	public void deselect(Player p) {}
	
	// Almost all will default to these values, might as well have them here
	@Override
	public int getTextureState() {
		return 0;
	}

	@Override
	public int getHighestState() {
		return 0;
	}
	
	@Override
	public String getTextureKey() {
		return this.tileName;
	}
	
	@Override
	public String getTextureLocation() {
		return "textures/tiles/" + this.tileName + ".png";
	}
	
	public boolean containsBones() {
		return containsBones;
	}
	
	public void setContainsBones(boolean containsBones) {
		this.containsBones = containsBones;
	}
	
}
