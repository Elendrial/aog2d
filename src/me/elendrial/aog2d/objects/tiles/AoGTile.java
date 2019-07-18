package me.elendrial.aog2d.objects.tiles;

import java.awt.Graphics;
import java.awt.Image;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.tiles.BaseTile;
import me.hii488.registries.TextureRegistry;

public abstract class AoGTile extends BaseTile {
	
	private boolean containsBones;
	private int tileSet = 0;
	
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

	@Override // NB: this is # of states, starting from 0. IE: _NOT LIKE SIZE()_
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
	
	public boolean containsBones() { // TODO: Make this affect graphics
		return containsBones;
	}
	
	public void setContainsBones(boolean containsBones) {
		this.containsBones = containsBones;
	}
	public int getTileSet() {
		return tileSet;
	}
	public AoGTile setTileSet(int tileSet) {
		this.tileSet = tileSet;
		return this;
	}
	
	public void render(Graphics g, Vector position) {
		Image backgroundImage = TextureRegistry.getTexture("tileBackground", getTileSet());
		g.drawImage(backgroundImage, position.getIX(), position.getIY(), null);
		
		super.render(g, position);
	}
	
}
