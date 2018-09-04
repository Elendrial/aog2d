package me.elendrial.aog2d.graphics.inGame;

import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.hii488.graphics.gui.GUISet;
import me.hii488.graphics.gui.premadeTypes.GUIOption;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;
import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.graphics.gui.style.GUIStyle.BackgroundStyle;

public class AoGGuiObjects {
	
	// Standard gui sets
	
	public static GUISet summonSet = new GUISet();
	
	
	// Standard gui elements
	
	public static GUIOptionBox getSummonGUI(Player p) {
		GUIOptionBox box = new GUIOptionBox(AoGStyleGroup.getInstance().styles.get("summonMenu"));
		
		p.alignmentLevel.entrySet().forEach(e -> box.addOption(generateGodOption(e.getKey(), e.getValue())));
		
		return box;
	}
	
	private static GUIOption generateGodOption(God g, int level) {
		GUIStyle defaultStyle = AoGStyleGroup.getInstance().styles.get("summonMenu");
		GUIStyle s = new GUIStyle(defaultStyle.textStyle, new BackgroundStyle(defaultStyle.backgroundStyle.getBackgroundColor(), "")); // TODO: Make these and put them somewhere like textures/gui/godIcons/<godname>.png
		
		GUIOption o = new GUIOption(s) {
			public void onSelect() {
				
			}
		};
		
		// On click must go and remove current 'outer' unit summon menu if it exits, and replace it with the new one.
		
		return o;
	}
	
	//private static GUIOptionBox generateGodOptionList(God g, int level) {
	//	
	//}
}
