package me.elendrial.aog2d.objects.units;

import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.elendrial.aog2d.graphics.inGame.GUITileHighlight;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.gameObjects.tiles.BaseTile;
import me.hii488.graphics.gui.GUISet;
import me.hii488.handlers.LevelHandler;

public abstract class Unit extends GridEntity implements IUpdating {
	
	// Things that may also be needed: Attack range. Movement modifiers outside of class
	
	private String name;
	private Player player;
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public int health;
	public int maxHealth;
	
	abstract public void onStartTurn();
	abstract public void onMove(); // TODO: have this take in an int for how far.
	abstract public void onAttack(int distance);
	abstract public void onEndTurn();
	
	abstract public int getCost();
	abstract public UnitClass getUnitClass();
	abstract public double getMovementDistance();
	abstract public boolean isSummonable();
	abstract public int[] attackRange();
	
	public Unit setPlayer(Player p) {
		player = p;
		return this;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void select(Player p) { // TODO: Attack square highlighting and figuring out - Movement highlighting
		if(p.equals(player)) { // Only the correct player can move the unit
			
			// I promise this is just Dijkstra
			// TODO: Rewrite this so it's not a mess (maybe look through Algs & Data Structs module notes?)
			
			HashMap<Vector, Double> movementCost = new HashMap<Vector, Double>();
			HashMap<Vector, ArrayList<Vector>> path = new HashMap<Vector, ArrayList<Vector>>();
			ArrayList<Vector> exploredLocations = new ArrayList<Vector>();
			
			Grid<BaseTile> tileGrid = LevelHandler.getCurrentLevel().getTileGrid();
			Grid<GridEntity> entityGrid = LevelHandler.getCurrentLevel().getEntityGrid();
			
			ArrayList<Vector> base = new ArrayList<>();
			base.add(gridPosition);
			
			movementCost.put(gridPosition, 0D);
			path.put(gridPosition, base);
			
			boolean foundNew = true;
			while(foundNew) {
				foundNew = false;
				
				for(Vector v : path.keySet()) {
					if(!exploredLocations.contains(v)) {
						exploredLocations.add(v);
						
						boolean allowed = true;
						Unit u = (Unit) entityGrid.getObjectAt(v);
						if(u != null) // Just assume you can't go through units
							allowed = false;
						else {
							AoGTile t = (AoGTile) tileGrid.getObjectAt(v);
							allowed = t.canPassThrough();
						}
						
						if(movementCost.get(v) >= getMovementDistance()) allowed = false;
						
						if(allowed) {
							foundNew = true;
							Vector[] adjacents = {new Vector(-1,0),new Vector(1,0),new Vector(0,-1),new Vector(0,1)};
							
							for(Vector adjacent : adjacents) {
								Vector adj = v.getLocation().translate(adjacent);
								double tileCost = ((AoGTile) tileGrid.getObjectAt(v)).movementCost(this);
								if(movementCost.containsKey(adj)) {
									if(movementCost.get(adj) > movementCost.get(v) + tileCost) {
										ArrayList<Vector> newPath = new ArrayList<>(path.get(v));
			                            newPath.add(adj);
			                            
			                            movementCost.put(adj, movementCost.get(v) + tileCost);
										path.put(adj, newPath);
										
										// TODO: Check that this wont cause an infinite loop of constantly lowering min path dist until either a crash or wraparound
										if(exploredLocations.contains(adj)) exploredLocations.remove(adj);
									}
								}
								else {
									ArrayList<Vector> newPath = new ArrayList<>(path.get(v));
		                            newPath.add(adj);
		                            
		                            movementCost.put(adj, movementCost.get(v) + tileCost);
									path.put(adj, newPath);
								}
							}
						}
					}
				}
			}
			
			// TODO: Combine this with the loop above?
			GUISet highlightSet = new GUISet();
			for(Vector v : exploredLocations) {
				if(movementCost.get(v) < this.getMovementDistance()) {
					GUITileHighlight tilehighlight = new GUITileHighlight(this, v);
					tilehighlight.getStyle().backgroundStyle.setTextureKey("moveOverlay");
					tilehighlight.setDimensions(new Vector(30,30));
					highlightSet.addElement(tilehighlight);
				}
			}
			this.parentLevel.getGUI().addGUISet(highlightSet);
			
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
