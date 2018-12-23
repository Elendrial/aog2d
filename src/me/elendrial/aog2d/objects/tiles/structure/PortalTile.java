package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.graphics.inGame.AoGGuiObjects;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;

public class PortalTile extends StructureTile{
	
	public boolean thisSummonMenuOpen = false;
	
	public PortalTile() {
		this.canPassThrough = true;
		this.tileName = "portal";
	}
	
	public void onLoad() {
		super.onLoad();
	}
	
	@Override
	public void select(Player p) {
	/*	BarbarianUnit b = (BarbarianUnit) EntityRegistry.getEntity("barbarian");
		b.setGridPosition(getGrid().getPositionOf(this));
		LevelHandler.getCurrentLevel().addEntity(b);*/
		
		closeSummonMenus();
		
		if(!thisSummonMenuOpen)
			openSummonMenu(p);
		else
			thisSummonMenuOpen = false;
	}
	
	public void deselect(Player p) {
		closeSummonMenus();
		//thisSummonMenuOpen = false;
	}
	
	public void openSummonMenu(Player p) {
		GUIOptionBox ob = AoGGuiObjects.getSummonGUI(p);
		
		ob.setPosition(this.getPosition());
		ob.show();

		thisSummonMenuOpen = true;
	}
	
	public static void closeSummonMenus() {
		AoGGuiObjects.summonSet.hideAllWithTag("summonMenu");
	}

}
