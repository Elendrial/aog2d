package me.elendrial.aog2d.objects.units;

import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.hii488.gameObjects.entities.GridEntity;

public abstract class Unit extends GridEntity implements IUpdating {
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public void onClick() {
		// Brings up movement highlighting etc.
	}
	
}
