package me.elendrial.aog2d.levels;

import me.elendrial.aog2d.gameSystems.turns.TurnController;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.hii488.EngineSettings;
import me.hii488.gameObjects.levels.BaseLevel;

public class AoGLevel extends BaseLevel {
	
	private TurnController turnController;
	
	public AoGLevel() {
		super();
		
		// Temporary, should be replaced with a proper map loader
		this.getTileGrid().setGridScale(EngineSettings.Texture.tileSize);
		this.getTileGrid().setDimensions(10);
		// TODO: Check why the fuck this doesn't produce what it should
		this.getTileGrid().fillDimensionsWith(0, 0, getTileGrid().getWidth(), getTileGrid().getHeight(), OutsideWallTile.class); // TODO: Create wallDimensionsWith() in Grid
		this.getTileGrid().fillDimensionsWith(1, 1, getTileGrid().getWidth()-1, getTileGrid().getHeight()-1, OpenTile.class);
		
		this.getEntityGrid().autoSetup(EngineSettings.Texture.tileSize);
	}
	
}
