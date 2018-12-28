package me.elendrial.aog2d;

import java.awt.Color;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.players.builders.StandardPlayerBuilder;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.elendrial.aog2d.objects.units.BarbarianChieftainUnit;
import me.elendrial.aog2d.objects.units.BarbarianUnit;
import me.hii488.controllers.InitialisationController;
import me.hii488.handlers.LevelHandler;
import me.hii488.interfaces.IInitialiser;
import me.hii488.registries.EntityRegistry;
import me.hii488.registries.TileRegistry;

public class Initialisation implements IInitialiser{

	public static void setup() {
		InitialisationController.addInitialiser(new Initialisation());
	}
	
	
	
	
	@Override
	public void preInit() {
		registerTiles();
		registerEntities();
		
		alignUnits();
	}
	
	public void registerTiles() {
		// 'Standard' Tiles
		TileRegistry.registerTile(new OutsideWallTile());
		TileRegistry.registerTile(new OpenTile());
		
		// 'Action Tiles'
		TileRegistry.registerTile(new PortalTile());
	}
	
	public void registerEntities() {
		EntityRegistry.registerEntity(new BarbarianUnit());
		EntityRegistry.registerEntity(new BarbarianChieftainUnit());
	}
	
	public void alignUnits() {
		// Setup God/Unit affiliations
		God.NEUTRAL.addUnit(BarbarianUnit.class);
		God.NEUTRAL.addUnit(BarbarianChieftainUnit.class);
	}

	@Override
	public void init() {
		// TODO: Add menus
		
		// Temporary setup, AoGLevel should be more dynamically created from a menu with options etc.
		Player p1 = new StandardPlayerBuilder().setColor(Color.RED).build();
		Player p2 = new StandardPlayerBuilder().setColor(Color.BLUE).build();
		LevelHandler.addLevel(new AoGLevel(new Player[] {p1,p2}), "aogLevel");
	}

	@Override
	public void postInit() {
		LevelHandler.loadLevel("aogLevel");
	}

}
