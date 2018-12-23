package me.elendrial.aog2d.graphics.inGame;

import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.graphics.gui.style.GUIStyleGroup;

public class AoGStyleGroup extends GUIStyleGroup{
	
	private static AoGStyleGroup group;
	
	// No need to have multiple versions of this, and you want to be able to change a setting once and have it stay the same for all versions of the GUI, right?
	public static GUIStyleGroup getInstance() {
		if(group == null) group = new AoGStyleGroup();
		
		return group;
	}
	
	protected AoGStyleGroup() {
		super();
		
		// TODO: Add GUIStyles... ("unitSummonMenu")
		GUIStyle summonMenu = GUIStyle.getDefault();
		summonMenu.metaStyle.dimensions.setLocation(20, 20);
		addStyle("summonMenu", summonMenu);
		
		GUIStyle unitSummonMenu = GUIStyle.getDefault();
		unitSummonMenu.metaStyle.dimensions.setLocation(20, 20);
		addStyle("unitSummonMenu", unitSummonMenu);
		
	}
	
}