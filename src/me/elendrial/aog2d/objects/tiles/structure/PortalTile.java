package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.graphics.inGame.AoGGuiObjects;
import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.graphics.gui.premadeTypes.GUIOptionBox;

public class PortalTile extends StructureTile{
		
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
		openSummonMenu(p);
	}
	
	public void deselect(Player p) {
		closeSummonMenus();
		//thisSummonMenuOpen = false;
	}
	
	public void openSummonMenu(Player p) {
		GUIOptionBox ob = AoGGuiObjects.getSummonGUI(p);
		
		ob.setPosition(this.getPosition());
		ob.show();
	}
	
	public static void closeSummonMenus() {
		AoGGuiObjects.summonSet.hideAllWithTag("summonMenu");
	}

	@Override
	public double movementCost(Unit unit) {
		return 1;
	}

}
