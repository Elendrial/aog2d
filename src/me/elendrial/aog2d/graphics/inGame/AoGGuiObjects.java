package me.elendrial.aog2d.graphics.inGame;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.EngineSettings;
import me.hii488.controllers.GameController;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUIElement;
import me.hii488.graphics.gui.GUISet;
import me.hii488.graphics.gui.GUISet.Priority;
import me.hii488.graphics.gui.premadeTypes.GUIOption;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;
import me.hii488.graphics.gui.premadeTypes.GUIStandardBox;
import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.graphics.gui.style.GUIStyle.BackgroundStyle;
import me.hii488.handlers.LevelHandler;

public class AoGGuiObjects {
	// TODO: Take into account Player level & mana/resources - Maybe generate menus as we do and then add 'covers' so you can't actually press the button?
	
	//// GUI sets and saved groups ////
	
	public static GUISet standardUI;
	
	public static GUISet summonSet = new GUISet().setPriority(Priority.MID);
	public static HashMap<Player, GUIOptionBox> godSummonOptionBox = new HashMap<Player, GUIOptionBox>(); // TODO: Possibly remove this one, as the others will essentially save it anyway?
	public static HashMap<String, GUIOption> godSummonOptions = new HashMap<String, GUIOption>();
	public static HashMap<String, GUIOptionBox> unitSummonMenus = new HashMap<String, GUIOptionBox>();
	
	//// Standard GUI elements ////
	
	public static GUISet getStandardUI() {
		if(standardUI != null) return standardUI;
		standardUI = new GUISet().setPriority(Priority.HIGH);
		
		standardUI.addElement(getEndTurnButton());
		
		// Something odd can happen with the styles here. Gotta a) look into that and b) be careful if/when this is changed.
		standardUI.addElement(new GUITurnIndicator(AoGStyleGroup.getInstance().getDefault()).setPosition(new Vector(GameController.getWindow().width/2-250, 0)));
		standardUI.addElement(new GUIManaIndicator(AoGStyleGroup.getInstance().getDefault()).setPosition(new Vector(GameController.getWindow().width/2-125, 0)));
		
		// Needs: time left (if applicable)
		
		return standardUI;
	}
	
	
	private static GUIElement getEndTurnButton() {
		return new GUIStandardBox(AoGStyleGroup.getInstance().getStyle("buttonStyle")) {
			public boolean onClick(MouseEvent e){
				((AoGLevel) LevelHandler.getCurrentLevel()).turnController.nextTurn();
				return true;
			}
		}.setText("END TURN")
		.setPosition(new Vector(GameController.getWindow().width/2, 0))
		.setDimensions(new Vector(100, 30))
		.setElementName("EndTurnButton");
	}
	
	
	
	//// GUI Summon elements ////	
	
	public static GUIOptionBox getSummonGUI(Player p) {
		if(godSummonOptionBox.containsKey(p)) return godSummonOptionBox.get(p);
		
		GUIOptionBox box = new GUIOptionBox(AoGStyleGroup.getInstance().getStyle("summonMenu")) {
			public boolean onClick(MouseEvent e) {
				boolean updated = false;
				// Using this as the reference point only works because I _know_ that all the options are anchored to this.
				Vector clickPos = this.getAnchoredPositionFromScreenPosition(e.getX(), e.getY());
				System.out.println(e.getX() + "," + e.getY() + "\t" + clickPos);
				for(int i = 0; i < options.size(); i++) {
					System.out.println(options.get(i).getBoundingBox());
					if(options.get(i).getBoundingBox().containsPoint(clickPos)) {
						updated = true;
						options.get(i).onSelect();
					}
				}
				
				return updated;
			}
		};
		box.setAnchor(LevelHandler.getCurrentLevel());
		box.addTag("summonMenu");
		box.setElementName("summonMenuBox" + p.color);
		
		p.alignmentLevel.entrySet().forEach(e -> box.addOption(generateGodOption(p, e.getKey(), e.getValue())));
		
		ArrayList<GUIOption> options = box.getOptions(); // God Options
		for(int i = 0; i < options.size(); i++) {
			Vector pos = new Vector(0, -30);														 // How far away from the portal/center it is
			pos.rotateDeg(360/options.size() * i);													 // Rotate into correct position.
			pos.translate(EngineSettings.Texture.tileSize/2 - options.get(i).getDimensions().getIX()/2, EngineSettings.Texture.tileSize/2 - options.get(i).getDimensions().getIY()/2); // Center on middle of tile
			options.get(i).setPosition(pos);
		}
		
		summonSet.addElement(box);
		godSummonOptionBox.put(p, box);
		
		return box;
	}
	
	private static GUIOption generateGodOption(Player p, God g, int level) {
		if(godSummonOptions.containsKey(g.name)) return godSummonOptions.get(g.name);
		
		GUIStyle defaultStyle = AoGStyleGroup.getInstance().getStyle("summonMenu");
		GUIStyle s = new GUIStyle(defaultStyle.metaStyle, defaultStyle.textStyle, new BackgroundStyle(defaultStyle.backgroundStyle.getBackgroundColor(), defaultStyle.backgroundStyle.getBorderColor(), g.name + "_icon", 0)); // TODO: Improve these icons
		
		if(!unitSummonMenus.containsKey(g.name + "_units")) unitSummonMenus.put(g.name + "_units", generateUnitOptionList(p, g)); // Shouldn't really be needed considering we're already saving the whole thing... may remove unitSummonMenus
		
		// TODO: make sure this isn't too inefficient.
		GUIOption o = new GUIOption(s) {
			GUIOptionBox unitOptionBox = unitSummonMenus.get(g.name + "_units");
			public void onSelect() {
			// TODO: Figure out why the parentGuiSet isn't being set for the option. TODO: Find out if the previous todo is relevant or not.
				parentBox.getParentGuiSet().hideAllWithTag("unitOptions");
				summonSet.addElement(unitOptionBox);
				unitOptionBox.setPosition(Vector.ORIGIN);
				unitOptionBox.setAnchor(parentBox);
				unitOptionBox.show();
			}
		};
		
		o.addTag("summonMenu");
		o.setElementName(g.name + "_summonOption");
		return o;
	}
	
	private static GUIOptionBox generateUnitOptionList(Player p, God g) {
		GUIStyle defaultStyle = AoGStyleGroup.getInstance().getStyle("unitSummonMenu");
		
		GUIOptionBox unitMenu = new GUIOptionBox(defaultStyle) {
			public boolean onClick(MouseEvent e) {
				boolean updated = false;
				for(int i = 0; i < options.size(); i++) {
					if(options.get(i).getBoundingBox().containsPoint(e.getX() - position.getIX(), e.getY() - position.getIY())) {
						updated = true;
						options.get(i).onSelect();
					}
				}
				
				return updated;
			}
		};
		
		for(Class<? extends Unit> unit : g.units){
			try {
				if(unit.newInstance().isSummonable()) {
					// TODO: Change these to display cost & stats etc - maybe on hover
					GUIStyle s = new GUIStyle(defaultStyle.metaStyle, defaultStyle.textStyle, new BackgroundStyle(defaultStyle.backgroundStyle.getBackgroundColor(), defaultStyle.backgroundStyle.getBorderColor(), unit.newInstance().getTextureKey(), 0));
					
					GUIOption o = new GUIOption(s) {
						public void onSelect() {
							parentBox.getParentGuiSet().hideAllWithTag("summonMenu");
							Unit u;
							Player player = ((AoGLevel) LevelHandler.getCurrentLevel()).turnController.getCurrentPlayer();
							try {
								u = unit.newInstance();
								if(u.isEligible(player) && u.getCost() <= player.getMana()) {
									u.setGridPosition(LevelHandler.getCurrentLevel().getEntityGrid().getGridVectorFromRealPosition(parentBox.getPosition()));
									u.setPlayer(player);
									LevelHandler.getCurrentLevel().addEntity(u);
									u.onSummon();
									player.addMana(-u.getCost());
								}
							} catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
						}
					};
					
					
					o.addTag("summonMenu");
					o.addTag("unitOptions");
					unitMenu.addOption(o);
				}
			} catch (InstantiationException | IllegalAccessException e1) {e1.printStackTrace();}
		}
		
		// Position the options separately to allow for some units not to be shown.
		for(int i = 0; i < unitMenu.getOptions().size(); i++)
			unitMenu.getOptions().get(i).setPosition(new Vector(0, -60).rotateDeg((360/unitMenu.getOptions().size()) * i));
		
		
		unitMenu.addTag("unitOptions");
		unitMenu.addTag("summonMenu");
		
		return unitMenu;
	}
	
}
