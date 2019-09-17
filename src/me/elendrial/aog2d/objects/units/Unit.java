package me.elendrial.aog2d.objects.units;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
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

public abstract class Unit extends GridEntity implements IUpdating{
	
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
	
	public int[] attackRange;
	public boolean isSummonable;
	public int cost;
	public UnitType ut;
	public int movementDistance;
	
	public int eligableLevel;
	public God eligableGod;
	
	public void onMove() {}; // TODO: Maybe have this take in start point and end point?
	public void onAttack(int distance) {};
	public boolean onDeath() {return true;} // Returns whether the unit still dies
	
	public int getAttacksPerTurn() {return 1;}
	
	public void onSummon() {
		registerAsUpdating((AoGLevel) parentGrid.getParentLevel());
		
		God alignedGod = God.unitAlignment(this);
		int level = player.getAlignmentLevel(alignedGod);
		if(level == eligableLevel) player.setAlignmentLevel(alignedGod, level + 1);
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
	public void select(Player p) {
		if(p.equals(player) && attacksLeft > 0) { // Only the correct player can move the unit
			
			// Find and highlight all movable tiles
			HashMap<Vector, Double> movementCost = getLocationsWithinMovementDistance();
			GUISet highlightSet = ((AoGLevel) parentLevel).tileOverlayGUISet;
			for(Vector v : movementCost.keySet()) {
				GUIMoveTileHighlight tilehighlight = new GUIMoveTileHighlight(this, v, movementCost.get(v));
				highlightSet.addElement(tilehighlight);
			}
			
			// Find attackables from location
			for(Unit u : getAttackableUnitsFromPosition(this.gridPosition.getCopy())) {
				GUIAttackTileHighlight tilehighlight = new GUIAttackTileHighlight(this, u.gridPosition);
				highlightSet.addElement(tilehighlight);
			}
		}
	}
	
	public HashMap<Vector, Double> getLocationsWithinMovementDistance(){
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
			Vector v = gridPosition.getCopy().translate(adjacent);
			
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
							adj = v.getCopy().translate(adjacent);
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
		
		HashMap<Vector, Double> allowedcosts = new HashMap<>();
		for(Vector v : allowedLocations) {
			allowedcosts.put(v, movementCost.get(v));
		}
		
		return allowedcosts;
	}

	public ArrayList<Unit> getAttackableUnitsFromPosition(Vector v){
		Grid<GridEntity> entityGrid = LevelHandler.getCurrentLevel().getEntityGrid();
		Vector start = v.getCopy().translate(-attackRange()[attackRange().length-1], -attackRange()[attackRange().length-1]), temp;
		ArrayList<Unit> attackables = new ArrayList<>();
		
		for(int i = 0; i < attackRange()[attackRange().length-1]*2 + 1; i++) {
			for(int j = 0; j < attackRange()[attackRange().length-1]*2 + 1; j++) {
				
				temp = start.getCopy().translate(i,j);
				Unit u = (Unit) entityGrid.getObjectAt(temp);
				
				if(u != null) {
					if(u.getPlayer() != player) {
						for(int allowedDist : attackRange()) {
							if(Math.round(u.gridPosition.distance(gridPosition)) == allowedDist) {
								if(canAttackUnit(u)) {
									attackables.add(u);
								}
							}
						}
					}
				}
				
			}
		}
		
		return attackables;
	}
	
	// Exists solely to be overridden. Checked only when the first unit tries to attack.
	public boolean canAttackUnit(Unit u) {
		return true;
	}
	
	public boolean withinRange(Unit u) {
		int dist = (int) u.getGridPosition().distance(getGridPosition());
		boolean can = false;
		for(int i : attackRange()) if(dist == i) can = true;
		
		return can;
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
		
		if(attacked.getHealth() >= 1 && attacked.withinRange(this)) this.damage(attacked.getAttack(this));
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
	
	public int getCost() {
		return cost;
	}
	
	public UnitType getUnitType() {
		return ut;
	}
	
	public double getMovementDistance() {
		return movementDistance;
	}
	
	public boolean isSummonable() {
		return isSummonable;
	}
	
	public int[] attackRange() {
		return attackRange;
	}
	
	public boolean isEligible(Player p) {
		if(eligableGod == null) return true;
		return p.getAlignmentLevel(eligableGod) >= eligableLevel;
	}
	
}
