package me.elendrial.aog2d.objects.units;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.hii488.gameObjects.entities.GridEntity;

public abstract class Unit extends GridEntity implements IUpdating {
	
	// Things that may also be needed: Attack range. Movement modifiers outside of class
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public void select(Player p) {} // TODO: show where the unit can move
	public void deselect(Player p) {}
	
	public int health;
	public int maxHealth;
	
	abstract public void onStartTurn();
	abstract public void onMove();
	abstract public void onAttack();
	abstract public void onEndTurn();
	
	abstract public int getCost();
	abstract public UnitClass getUnitClass();
	abstract public boolean isSummonable();
	
	public void onClick(Player p) {
		// Brings up movement highlighting etc.
		System.out.println("unit clicked");
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean damage(int i) {
		health -= i;
		if(health <= 0) {
			this.kill();
			return true;
		}
		return false;
	}
	
	public void heal(int i) {
		health += i;
		if(health > maxHealth) health = maxHealth;
	}
	
	public void kill() {
		// TODO
	}
	
	// Type advantages will be defined in dedicated attacking code.
	public enum UnitClass{
		SKIRMISH, WARRIOR, RANGER, MAGE, HELPER, FLYING, CREEPER, TITAN;
	}
	
}
