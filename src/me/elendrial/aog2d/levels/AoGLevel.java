package me.elendrial.aog2d.levels;

import me.elendrial.aog2d.gameSystems.ClickController;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.TurnController;
import me.elendrial.aog2d.graphics.inGame.AoGGuiObjects;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.environment.RoadTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.elendrial.aog2d.objects.units.BarbarianUnit;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.EngineSettings;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.levels.BaseLevel;
import me.hii488.graphics.gui.GUISet;
import me.hii488.handlers.InputHandler;
import me.hii488.handlers.LevelHandler;

public class AoGLevel extends BaseLevel {
	
	public TurnController turnController;
	public ClickController clickController;
	public Player[] players;
	public GUISet tileOverlayGUISet;
	private boolean initialised = false;
	
	// TODO: Probably separate this out into 'load map', 'load players', 'start game' phases.
	public AoGLevel(Player[] p) {
		super();
		
		players = p;
		
		turnController = new TurnController(players, this);
		clickController = new ClickController(this);
		tileOverlayGUISet = new GUISet();
		
		
		InputHandler.addLateInputListener(clickController);
		
		this.getEntityGrid().autoSetup(EngineSettings.Texture.tileSize);
		
		// Temporary, should be replaced with a proper map loader
		
		this.getTileGrid().setGridScale(EngineSettings.Texture.tileSize);
		this.getTileGrid().setDimensions(25);

		this.getTileGrid().fillDimensionsWith(0, 0, getTileGrid().getWidth(), getTileGrid().getHeight(), OutsideWallTile.class); // TODO: Create wallDimensionsWith() in Grid
		this.getTileGrid().fillDimensionsWith(1, 1, getTileGrid().getWidth()-1, getTileGrid().getHeight()-1, OpenTile.class);
		this.getTileGrid().fillDimensionsWith(6, 6, 20, 7, RoadTile.class);
		this.getTileGrid().fillDimensionsWith(3, 8, 5, 9, OutsideWallTile.class);
		this.getTileGrid().fillDimensionsWith(6, 8, 8, 9, OutsideWallTile.class);
		
		PortalTile pt = new PortalTile();
		pt.capturedBy = p[0];
		this.getTileGrid().setObjectAt(5, 6, pt);
		
		PortalTile pt2= new PortalTile();
		pt2.capturedBy = p[1];
		this.getTileGrid().setObjectAt(20, 20, pt2);
		
		BarbarianUnit u = new BarbarianUnit();
		u.setGridPosition(8, 6);
		u.setPlayer(p[1]);
		addEntity(u);
		u.onSummon();
		
		// End of temporary section
	}
	
	public void onLoad() {
		super.onLoad();
		
		if(!initialised) {
			AoGGuiObjects.getStandardUI();
			this.getGUI().addGUISet(AoGGuiObjects.standardUI);
			this.getGUI().addGUISet(AoGGuiObjects.summonSet);
			this.getGUI().addGUISet(tileOverlayGUISet);
			initialised = true;
		}
	}
	
	public void createNewUnit(Class<? extends Unit> unit, Vector gridPosition, Player p) {
		boolean playerAllowed = false;
		for(Player pl : players) {
			if(p == pl) {
				playerAllowed = true;
				break;
			}
		}
		
		if(playerAllowed) {
			try {
				Unit u = unit.newInstance();
				u.setGridPosition(gridPosition);
				u.setPlayer(p);
				LevelHandler.getCurrentLevel().addEntity(u);
				u.onSummon();
			} catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
		}
	}
	
	public void createNewUnit(Unit u, Vector gridPosition, Player p) {
		createNewUnit(u.getClass(), gridPosition, p);
	}
	
}
