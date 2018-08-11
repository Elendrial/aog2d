package me.elendrial.aog2d.levels;

import me.elendrial.aog2d.gameSystems.ClickController;
import me.elendrial.aog2d.gameSystems.turns.TurnController;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.hii488.EngineSettings;
import me.hii488.gameObjects.levels.BaseLevel;
import me.hii488.handlers.InputHandler;

public class AoGLevel extends BaseLevel {
	
	private TurnController turnController;
	private ClickController clickController;
	
	public AoGLevel() {
		super();
		
		clickController = new ClickController(this);
		InputHandler.addInputListener(clickController);
		
		// Temporary, should be replaced with a proper map loader
		
		this.getTileGrid().setGridScale(EngineSettings.Texture.tileSize);
		this.getTileGrid().setDimensions(10);

		this.getTileGrid().fillDimensionsWith(0, 0, getTileGrid().getWidth(), getTileGrid().getHeight(), OutsideWallTile.class); // TODO: Create wallDimensionsWith() in Grid
		this.getTileGrid().fillDimensionsWith(1, 1, getTileGrid().getWidth()-1, getTileGrid().getHeight()-1, OpenTile.class);
		
		this.getTileGrid().setObjectAt(5, 5, new PortalTile());
		
		this.getEntityGrid().autoSetup(EngineSettings.Texture.tileSize);
	}
	
}
