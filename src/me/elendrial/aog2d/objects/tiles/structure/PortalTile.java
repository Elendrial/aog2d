package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.graphics.inGame.AoGGuiObjects;
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
	public void onClick(Player p) {
		// TODO: Bring up summon menu, this is temp
	/*	BarbarianUnit b = (BarbarianUnit) EntityRegistry.getEntity("barbarian");
		b.setGridPosition(getGrid().getPositionOf(this));
		LevelHandler.getCurrentLevel().addEntity(b);*/
		
		GUIOptionBox ob = AoGGuiObjects.getSummonGUI(p);
		
		ob.setPosition(this.getPosition());
		ob.show();
		
	}

}
