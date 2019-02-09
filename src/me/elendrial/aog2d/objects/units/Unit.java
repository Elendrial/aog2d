package me.elendrial.aog2d.objects.units;

import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.elendrial.aog2d.graphics.inGame.GUIAttackTileHighlight;
import me.elendrial.aog2d.graphics.inGame.GUIMoveTileHighlight;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.levels.AoGLevel;
import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.gameObjects.tiles.BaseTile;
import me.hii488.graphics.gui.GUISet;
import me.hii488.handlers.LevelHandler;

public abstract class Unit extends GridEntity implements IUpdating {
	
	// Things that may also be needed: Attack range. Movement modifiers outside of class
	
	private Player player;
	private int attacksLeft;
	private double movementLeft;
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public int health;
	public int maxHealth;
	
	abstract public void onMove(); // TODO: have this take in an int for how far.
	abstract public void onAttack(int distance);
	abstract public void onEndTurn();
	
	abstract public int getCost();
	abstract public UnitClass getUnitClass();
	abstract public double getMovementDistance();
	abstract public boolean isSummonable();
	abstract public int[] attackRange(); // should start with lowest and end with highest
	
	public int getAttacksPerTurn() {return 1;}
	
	public void onSummon() {
		onStartTurn();
	}
	
	public void onStartTurn() {
		attacksLeft = getAttacksPerTurn();
		movementLeft = getMovementDistance();
	}
	
	public Unit setPlayer(Player p) {
		player = p;
		return this;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void select(Player p) { // TODO: Attack square highlighting and figuring out - Movement highlighting
		if(p.equals(player) && attacksLeft > 0) { // Only the correct player can move the unit
			
			// I promise this is just Dijkstra, I think, maybe it's nearer breadth first?
			// TODO: Rewrite this so it's not a mess (maybe look through Algs & Data Structs module notes?)
			
			HashMap<Vector, Double> movementCost = new HashMap<Vector, Double>();
			HashMap<Vector, ArrayList<Vector>> path = new HashMap<Vector, ArrayList<Vector>>(), tempPath = new HashMap<Vector, ArrayList<Vector>>();
			ArrayList<Vector> exploredLocations = new ArrayList<Vector>();
			ArrayList<Vector> allowedLocations = new ArrayList<Vector>();
			Vector[] adjacents = {new Vector(-1,0),new Vector(1,0),new Vector(0,-1),new Vector(0,1)};
			
			Grid<BaseTile> tileGrid = LevelHandler.getCurrentLevel().getTileGrid();
			Grid<GridEntity> entityGrid = LevelHandler.getCurrentLevel().getEntityGrid();
			
			ArrayList<Vector> base = new ArrayList<>();
			base.add(gridPosition);
			
			movementCost.put(gridPosition, 0D);
			path.put(gridPosition, base);
			exploredLocations.add(gridPosition);
			
			double tileCost;
			// Pre-add the adjacent tiles or it'll fail, adds a bit of mess.
			for(Vector adjacent : adjacents) {
				Vector v = gridPosition.getLocation().translate(adjacent);
				
				ArrayList<Vector> newPath = new ArrayList<>(path.get(gridPosition));
	            newPath.add(v);
	            
				tileCost = ((AoGTile) tileGrid.getObjectAt(v)).movementCost(this);
	            
	            movementCost.put(v, movementCost.get(gridPosition) + tileCost);
				path.put(v, newPath);
			}
			
			
			// Start actual Dijkstra
			boolean foundNew = true;
			Vector adj;
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
						
						if(movementCost.get(v) >= this.movementLeft) allowed = false;
						
						if(allowed) {
							foundNew = true;
							allowedLocations.add(v);
							
							for(Vector adjacent : adjacents) {
								adj = v.getLocation().translate(adjacent);
								tileCost = ((AoGTile) tileGrid.getObjectAt(v)).movementCost(this);
								if(movementCost.containsKey(adj)) {
									if(movementCost.get(adj) > movementCost.get(v) + tileCost) {
										ArrayList<Vector> newPath = new ArrayList<>(path.get(v));
			                            newPath.add(adj);
			                            
			                            movementCost.put(adj, movementCost.get(v) + tileCost);
										tempPath.put(adj, newPath);
										
										// TODO: Check that this wont cause an infinite loop of constantly lowering min path dist until either a crash or wraparound
										if(exploredLocations.contains(adj)) exploredLocations.remove(adj);
									}
								}
								else {
									ArrayList<Vector> newPath = new ArrayList<>(path.get(v));
		                            newPath.add(adj);
		                            
		                            movementCost.put(adj, movementCost.get(v) + tileCost);
		                            tempPath.put(adj, newPath);
								}
							}
						}
					}
				}
				
				path.putAll(tempPath);
				tempPath.clear();
			}
			
			// TODO: Combine this with the loop above?
			GUISet highlightSet = ((AoGLevel) parentLevel).tileOverlayGUISet;
			for(Vector v : allowedLocations) {
				GUIMoveTileHighlight tilehighlight = new GUIMoveTileHighlight(this, v);
				highlightSet.addElement(tilehighlight);
			}
			
			
			// Very basic attack checking. TODO: Improve this to be more like AoG
			Vector start = this.gridPosition.getLocation().translate(-attackRange()[attackRange().length-1], -attackRange()[attackRange().length-1]), temp;
			for(int i = 0; i < attackRange()[attackRange().length-1]*2; i++) {
				for(int j = 0; j < attackRange()[attackRange().length-1]*2; j++) {
					temp = start.getLocation().translate(i,j);
					Unit u = (Unit) entityGrid.getObjectAt(temp);
					if(u != null) {
						if(u.getPlayer() != player) {
							for(int allowedDist : attackRange()) {
								if(Math.round(u.gridPosition.distance(gridPosition)) == allowedDist) {
									GUIAttackTileHighlight tilehighlight = new GUIAttackTileHighlight(this, temp);
									highlightSet.addElement(tilehighlight);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void deselect(Player p) {
		// Hide all the movement highlighting.
		((AoGLevel) parentLevel).tileOverlayGUISet.empty();
	}
	
	public void move(Vector v) {
		((AoGLevel) parentLevel).tileOverlayGUISet.empty();
		((AoGLevel) this.parentLevel).clickController.deselect(player);
		this.setGridPosition(v);
		onMove();
	}
	
	public void attack(Vector v) {
		// TODO
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
	
	// Type advantages will be defined in dedicated attacking code.
	public enum UnitClass{
		SKIRMISH, WARRIOR, RANGER, MAGE, HELPER, FLYING, CREEPER, TITAN;
	}
	
}
