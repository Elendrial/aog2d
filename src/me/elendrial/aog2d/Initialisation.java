package me.elendrial.aog2d;

import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.hii488.controllers.InitialisationController;
import me.hii488.handlers.LevelHandler;
import me.hii488.interfaces.IInitialiser;
import me.hii488.registries.TileRegistry;

public class Initialisation implements IInitialiser{

	public static void setup() {
		InitialisationController.addInitialiser(new Initialisation());
	}
	
	
	
	
	@Override
	public void preInit() {
		// Register tiles/entities
		TileRegistry.registerTile(new OutsideWallTile());
		TileRegistry.registerTile(new OpenTile());
		
		TileRegistry.registerTile(new PortalTile());
		
		
		// Setup God/Unit affiliations
		
	}

	@Override
	public void init() {
		LevelHandler.addLevel(new AoGLevel(), "aogLevel");
	}

	@Override
	public void postInit() {
		LevelHandler.loadLevel("aogLevel");
	}

}
