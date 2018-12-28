package me.elendrial.aog2d.objects.units;

import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.GridEntity;

public abstract class Unit extends GridEntity implements IUpdating {
	
	// Things that may also be needed: Attack range. Movement modifiers outside of class
	
	private String name;
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public int health;
	public int maxHealth;
	
	abstract public void onStartTurn();
	abstract public void onMove();
	abstract public void onAttack();
	abstract public void onEndTurn();
	
	abstract public int getCost();
	abstract public UnitClass getUnitClass();
	abstract public boolean isSummonable();
	
	public void select(Player p) {
		HashMap<Vector, Double> movementCost = new HashMap<Vector, Double>();
		HashMap<Vector, ArrayList<Vector>> path = new HashMap<Vector, ArrayList<Vector>>();
		ArrayList<Vector> toSearch = new ArrayList<Vector>();
		
		toSearch.add(this.gridPosition.getLocation().translate(0,1));
		toSearch.add(this.gridPosition.getLocation().translate(0,-1));
		toSearch.add(this.gridPosition.getLocation().translate(-1,0));
		toSearch.add(this.gridPosition.getLocation().translate(1,0));
		
		while(toSearch.size() > 0) {
			for(Vector v : toSearch) {
				
			}
		}
		
	}
	
	public void deselect(Player p) {
		// Hide all the movement highlighting.
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
		// TODO: Unit death
	}
	
	public String getUnitName() {
		return name;
	}
	
	// Type advantages will be defined in dedicated attacking code.
	public enum UnitClass{
		SKIRMISH, WARRIOR, RANGER, MAGE, HELPER, FLYING, CREEPER, TITAN;
	}
	
}
