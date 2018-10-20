package me.elendrial.aog2d.graphics.inGame;

import java.util.ArrayList;
import java.util.HashMap;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.hii488.EngineSettings;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUISet;
import me.hii488.graphics.gui.premadeTypes.GUIOption;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;
import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.graphics.gui.style.GUIStyle.BackgroundStyle;

public class AoGGuiObjects {
	
	//// GUI sets and saved groups ////
	
	public static GUISet summonSet = new GUISet();
	public static HashMap<Player, GUIOptionBox> godSummonOptionBox = new HashMap<Player, GUIOptionBox>(); // TODO: Maybe remove some of these, like once we've got the godSummonOptionBox then it saves all the others...
	public static HashMap<String, GUIOption> godSummonOptions = new HashMap<String, GUIOption>();
	public static HashMap<String, GUIOptionBox> unitSummonMenus = new HashMap<String, GUIOptionBox>();
	
	
	//// GUI elements ////
	
	public static GUIOptionBox getSummonGUI(Player p) {
		if(godSummonOptionBox.containsKey(p)) return godSummonOptionBox.get(p);
		
		GUIOptionBox box = new GUIOptionBox(AoGStyleGroup.getInstance().getStyle("summonMenu"));
		box.addTag("summonMenu");
		
		p.alignmentLevel.entrySet().forEach(e -> box.addOption(generateGodOption(e.getKey(), e.getValue())));
		
		ArrayList<GUIOption> options = box.getOptions();
		for(int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i).getElementName());
			Vector pos = new Vector(0, -30);														 // How far away from the portal/center it is
			pos.rotateDeg(360/options.size() * i);												 // Rotate into correct position.
			pos.translate(EngineSettings.Texture.tileSize/2 - options.get(i).getDimensions().getIX()/2, EngineSettings.Texture.tileSize/2 - options.get(i).getDimensions().getIY()/2); // Center on middle of tile
			options.get(i).setPosition(pos);
		}
		
		summonSet.addElement(box);
		godSummonOptionBox.put(p, box);
		
		return box;
	}
	
	private static GUIOption generateGodOption(God g, int level) {
		if(godSummonOptions.containsKey(g.name)) return godSummonOptions.get(g.name);
		
		GUIStyle defaultStyle = AoGStyleGroup.getInstance().getStyle("summonMenu");
		GUIStyle s = new GUIStyle(defaultStyle.metaStyle, defaultStyle.textStyle, new BackgroundStyle(defaultStyle.backgroundStyle.getBackgroundColor(), g.name + "_icon", 0)); // TODO: Improve these icons
		
		if(unitSummonMenus.containsKey(g.name + "_units")) unitSummonMenus.put(g.name + "_units", generateUnitOptionList(g)); // Shouldn't really be needed considering we're already saving the whole thing... may remove unitSummonMenus
		
		// TODO: make sure this isn't too inefficient.
		GUIOption o = new GUIOption(s) {
			GUIOptionBox unitOptionBox = unitSummonMenus.get(g.name + "_units");
			
			public void onSelect() {
				this.parentGuiSet.hideAllWithTag("unitOptions");
				unitOptionBox.show();
			}
		};
		
		o.addTag("summonMenu");
		o.setElementName(g.name + "_summonOption");
		return o;
	}
	
	private static GUIOptionBox generateUnitOptionList(God g) {
		GUIStyle defaultStyle = AoGStyleGroup.getInstance().getStyle("unitSummonMenu");
		
		GUIOptionBox unitMenu = new GUIOptionBox(defaultStyle);
		
		int amount = g.units.size();
		for(int i = 0; i < amount; i++) {
			
			GUIOption o = new GUIOption(defaultStyle) {
				public void onSelect() {
					// TODO: Close all summon menus, summon the unit.
				}
			};
			
			Vector pos = new Vector(unitMenu.getDimensions().getX()/2, unitMenu.getDimensions().getY()/2);
			
			// TODO: Check that this is actually an appropriate distance from the center point.
			pos.translate(Math.cos(2 * Math.PI / amount) * unitMenu.getDimensions().getX()/3, Math.sin(2 * Math.PI / amount)* unitMenu.getDimensions().getX()/3);
			
			o.addTag("summonMenu");
			o.setPosition(pos);
			unitMenu.addOption(o);
		}
		
		return unitMenu;
	}
}
