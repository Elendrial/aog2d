package me.elendrial.aog2d;

import java.awt.Color;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.players.builders.StandardPlayerBuilder;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.environment.ForestTile;
import me.elendrial.aog2d.objects.tiles.environment.MountainTile;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.environment.RiverTile;
import me.elendrial.aog2d.objects.tiles.environment.RoadTile;
import me.elendrial.aog2d.objects.tiles.environment.SeaTile;
import me.elendrial.aog2d.objects.tiles.environment.SwampTile;
import me.elendrial.aog2d.objects.tiles.environment.WallTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.elendrial.aog2d.objects.tiles.structure.TempleTile;
import me.elendrial.aog2d.objects.tiles.structure.TowerTile;
import me.elendrial.aog2d.objects.tiles.structure.VillageTile;
import me.elendrial.aog2d.objects.units.neutral.BarbarianChieftainUnit;
import me.elendrial.aog2d.objects.units.neutral.BarbarianSpearUnit;
import me.elendrial.aog2d.objects.units.neutral.BarbarianUnit;
import me.hii488.controllers.InitialisationController;
import me.hii488.handlers.LevelHandler;
import me.hii488.interfaces.IInitialiser;
import me.hii488.registries.EntityRegistry;
import me.hii488.registries.TextureRegistry;
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
		
		UnitType.init();
	}
	
	public void registerTiles() {
		// 'Environment' Tiles
		TileRegistry.registerTile(new OutsideWallTile());
		TileRegistry.registerTile(new OpenTile());
		TileRegistry.registerTile(new RoadTile());
		TileRegistry.registerTile(new SwampTile());
		TileRegistry.registerTile(new RiverTile());
		TileRegistry.registerTile(new SeaTile());
		TileRegistry.registerTile(new ForestTile());
		TileRegistry.registerTile(new MountainTile());
		TileRegistry.registerTile(new WallTile());
		
		// 'Structure' Tiles
		TileRegistry.registerTile(new PortalTile());
		TileRegistry.registerTile(new VillageTile());
		TileRegistry.registerTile(new TowerTile());
		TileRegistry.registerTile(new TempleTile());
	}
	
	public void registerEntities() {
		EntityRegistry.registerEntity(new BarbarianUnit());
		EntityRegistry.registerEntity(new BarbarianChieftainUnit());
		EntityRegistry.registerEntity(new BarbarianSpearUnit());
	}
	
	public void alignUnits() {
		// Setup God/Unit affiliations
		God.NEUTRAL.addUnit(BarbarianUnit.class);
		God.NEUTRAL.addUnit(BarbarianChieftainUnit.class);
		God.NEUTRAL.addUnit(BarbarianSpearUnit.class);
	}

	@Override
	public void init() {
		initExtraTextures();
		// TODO: Add menus
		
		// Temporary setup, AoGLevel should be more dynamically created from a menu with options etc.
		Player p1 = new StandardPlayerBuilder().setColor(Color.RED).build();
		Player p2 = new StandardPlayerBuilder().setColor(Color.BLUE).build();
		LevelHandler.addLevel(new AoGLevel(new Player[] {p1,p2}), "aogLevel");
	}
	
	public void initExtraTextures() {
		TextureRegistry.addTexture("textures/gui/tileOverlay/attackOverlay.png", "attackOverlay", 1);
		TextureRegistry.addTexture("textures/gui/tileOverlay/moveOverlay.png", "moveOverlay", 1);
	}

	@Override
	public void postInit() {
		LevelHandler.loadLevel("aogLevel");
	}

}
