package me.elendrial.aog2d.levels;

import me.elendrial.aog2d.gameSystems.ClickController;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.TurnController;
import me.elendrial.aog2d.graphics.inGame.AoGGuiObjects;
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
		this.getTileGrid().setGridScale(EngineSettings.Texture.tileSize);
		
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
