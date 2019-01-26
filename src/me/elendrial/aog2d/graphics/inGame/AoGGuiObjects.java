package me.elendrial.aog2d.graphics.inGame;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.EngineSettings;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUISet;
import me.hii488.graphics.gui.premadeTypes.GUIOption;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;
import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.graphics.gui.style.GUIStyle.BackgroundStyle;
import me.hii488.handlers.LevelHandler;

public class AoGGuiObjects {
	// TODO: Take into account Player level & mana/resources - Maybe generate menus as we do and then add 'covers' so you can't actually press the button?
	
	//// GUI sets and saved groups ////
	
	public static GUISet summonSet = new GUISet();
	public static HashMap<Player, GUIOptionBox> godSummonOptionBox = new HashMap<Player, GUIOptionBox>(); // TODO: Possibly remove this one, as the others will essentially save it anyway?
	public static HashMap<String, GUIOption> godSummonOptions = new HashMap<String, GUIOption>();
	public static HashMap<String, GUIOptionBox> unitSummonMenus = new HashMap<String, GUIOptionBox>();
	
	
	//// GUI elements ////
	
	public static GUIOptionBox getSummonGUI(Player p) {
		if(godSummonOptionBox.containsKey(p)) return godSummonOptionBox.get(p);
		
		GUIOptionBox box = new GUIOptionBox(AoGStyleGroup.getInstance().getStyle("summonMenu")) {
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
		box.addTag("summonMenu");
		box.setElementName("summonMenuBox" + p.color);
		
		p.alignmentLevel.entrySet().forEach(e -> box.addOption(generateGodOption(p, e.getKey(), e.getValue())));
		
		ArrayList<GUIOption> options = box.getOptions();
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
		GUIStyle s = new GUIStyle(defaultStyle.metaStyle, defaultStyle.textStyle, new BackgroundStyle(defaultStyle.backgroundStyle.getBackgroundColor(), g.name + "_icon", 0)); // TODO: Improve these icons
		
		if(!unitSummonMenus.containsKey(g.name + "_units")) unitSummonMenus.put(g.name + "_units", generateUnitOptionList(p, g)); // Shouldn't really be needed considering we're already saving the whole thing... may remove unitSummonMenus
		
		// TODO: make sure this isn't too inefficient.
		GUIOption o = new GUIOption(s) {
			GUIOptionBox unitOptionBox = unitSummonMenus.get(g.name + "_units");
			public void onSelect() {
			// TODO: Figure out why the parentGuiSet isn't being set for the option
				parentBox.getParentGuiSet().hideAllWithTag("unitOptions");
				summonSet.addElement(unitOptionBox);
				unitOptionBox.setPosition(parentBox.getPosition());
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
					GUIStyle s = new GUIStyle(defaultStyle.metaStyle, defaultStyle.textStyle, new BackgroundStyle(defaultStyle.backgroundStyle.getBackgroundColor(), unit.newInstance().getTextureKey(), 0));
				
					GUIOption o = new GUIOption(s) {
						public void onSelect() {
							parentBox.getParentGuiSet().hideAllWithTag("summonMenu");
							Unit u;
							try {
								u = unit.newInstance();
								u.setGridPosition(LevelHandler.getCurrentLevel().getEntityGrid().getGridVectorFromRealPosition(parentBox.getPosition()));
								u.setPlayer(p);
								// TODO: Set the unit's player
								LevelHandler.getCurrentLevel().addEntity(u);
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
