package me.elendrial.aog2d.objects.units;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.UnitType;
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
	
	// Things that may also be needed: Movement modifiers outside of class
	// TODO: Add a graphic to display when a unit has run out of movement.
	// TODO: onSpecialAction()
	
	protected Player player;
	private int attacksLeft;
	private double movementLeft;
	
	public void onLoad() {}
	public void onUnload() {}
	public void updateOnTick() {}
	public void updateOnSec() {}
	
	public int health;
	public int maxHealth;
	public boolean poisoned = false;
	protected int buff = 0;
	
	abstract public void onMove(); // TODO: Maybe have this take in start point and end point?
	abstract public void onAttack(int distance);
	public boolean onDeath() {return true;} // Returns whether the unit still dies
	
	abstract public int getCost();
	abstract public boolean isEligible(Player p);
	abstract public UnitType getUnitType();
	abstract public double getMovementDistance();
	abstract public boolean isSummonable();
	abstract public int[] attackRange(); // should start with lowest and end with highest
	
	public int getAttacksPerTurn() {return 1;}
	
	public void onSummon() {
		registerAsUpdating((AoGLevel) parentGrid.getParentLevel());
	}
	
	@Override
	public void onTurnStart(Player p) {
		attacksLeft = getAttacksPerTurn();
		movementLeft = getMovementDistance();
		
		if(poisoned) {
			// TODO: Poison damage
		}
		
		if(buff > 0) {
			damage(1);
		}
	}
	
	public Unit setPlayer(Player p) {
		player = p;
		return this;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	// TODO: If setting movement cost set to 1000000 doesn't stop moving through, add a "canPass(Unit u)" method to tiles - NB: not UnitType so that specific special units can pass
	// TODO: Split this up or condense it.
	public void select(Player p) { // TODO: Attack square highlighting and figuring out - Movement highlighting
		if(p.equals(player) && attacksLeft > 0) { // Only the correct player can move the unit
			
			// I promise this is just Dijkstra, I think
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
				GUIMoveTileHighlight tilehighlight = new GUIMoveTileHighlight(this, v, movementCost.get(v));
				highlightSet.addElement(tilehighlight);
			}
			
			
			// Very basic attack checking. TODO: Improve this to be more like AoG
			// Idea on how to improve: On every new vector added to movement, check all units previously unattackable(ie: not in map). If any is in the correct range, add it & and the vector to move to to a map
			Vector start = this.gridPosition.getLocation().translate(-attackRange()[attackRange().length-1], -attackRange()[attackRange().length-1]), temp;
			for(int i = 0; i < attackRange()[attackRange().length-1]*2 + 1; i++) {
				for(int j = 0; j < attackRange()[attackRange().length-1]*2 + 1; j++) {
					temp = start.getLocation().translate(i,j);
					Unit u = (Unit) entityGrid.getObjectAt(temp);
					if(u != null) {
						if(u.getPlayer() != player) {
							for(int allowedDist : attackRange()) {
								if(Math.round(u.gridPosition.distance(gridPosition)) == allowedDist) {
									if(canAttackUnit(u)) {
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
	}
	
	// Here entirely to be overwritten
	public boolean canAttackUnit(Unit u) {
		return true;
	}
	
	public void deselect(Player p) {
		// Hide all the movement highlighting.
		((AoGLevel) parentLevel).tileOverlayGUISet.empty();
	}
	
	public void move(Vector v, double d) {
		((AoGLevel) parentLevel).tileOverlayGUISet.empty();
		((AoGLevel) this.parentLevel).clickController.deselect(player);
		this.setGridPosition(v);
		this.movementLeft -= d;
		onMove();
	}
	
	public void attack(Vector v) {
		((AoGLevel) parentLevel).tileOverlayGUISet.empty();
		((AoGLevel) this.parentLevel).clickController.deselect(player);
		this.attacksLeft--;
		
		Unit attacked = (Unit) parentGrid.getObjectAt(v);
		attacked.damage(this.getAttack(attacked));
		
		// TODO: Change this so opponent can only attack back if in range.
		if(attacked.getHealth() >= 1) this.damage(attacked.getAttack(this));
	}
	
	public void buff(int i) {
		buff += i;
	}
	
	public int getHealth() {
		return health + buff;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getAttack(Unit attacked) {
		float strength = this.getHealth();
		strength *= this.getUnitType().getAttackModifier(attacked.getUnitType());
		strength -= strength * attacked.getTile().defenceBonus(attacked);
		
		return strength > 1 ? (int) strength : 1; // must do 1+ damage
	}
	
	public boolean damage(int i) {
		if(buff > 0) { buff -= i;
			if(buff < 0) {
				health += buff;
				buff = 0;
			}
		}
		else health -= i;
		
		if(health <= 0) {
			this.kill();
			return true;
		}
		return false;
	}
	
	public void heal(int i) {
		health += i;
		if(health > maxHealth) health = maxHealth; // TODO: Check whether units can go over their max health
	}
	
	public void kill() {
		// TODO: Unit death
		if(!this.onDeath()) return;
		
		// Give points maybe?
		getTile().setContainsBones(true);
		this.destroy();
	}
	
	@Override
	public void render(Graphics g) {
		//super.render(g);
		
		// Temporary change to how the image is rendered so that it renders fully within a tile
		Vector absPos = getAbsPosition();
		//int tileSize = parentGrid.getGridScale(); // Grid may not line up with tiles.
		
		// Render in the middle of the grid 'tile'
		g.drawImage(getTexture(), absPos.getIX(), absPos.getIY(), 30, 30, null);
		
		
		///// Temporary way of showing which side a unit it on & health
		Color c= g.getColor();
		g.setColor(getPlayer().color);
		g.drawLine(getAbsPosition().getIX(), getAbsPosition().getIY() + 30, getAbsPosition().getIX() + 30 * this.getHealth()/this.getMaxHealth(), getAbsPosition().getIY() + 30);
		g.drawString(this.getHealth() + "", getAbsPosition().getIX(), getAbsPosition().getIY());
		g.setColor(c);
	}
	
	public AoGTile getTile() {
		return (AoGTile) getParentLevel().getTileGrid().getObjectAt(gridPosition);
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
	public String getTextureLocation() {
		return "textures/units/" + this.entityName + ".png";
	}
	
}
