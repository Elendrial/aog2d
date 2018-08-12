package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.objects.units.BarbarianUnit;
import me.hii488.handlers.LevelHandler;
import me.hii488.registries.EntityRegistry;

public class PortalTile extends StructureTile{

	public PortalTile() {
		this.canPassThrough = true;
		this.tileName = "portal";
	}
	
	public void onLoad() {
		super.onLoad();
	//	System.out.println(this.parentGrid.getPositionOf(this).scale(parentGrid.getGridScale()));
	}
	
	@Override
	public void onClick(Player p) {
		// TODO: Bring up summon menu, this is temp
		BarbarianUnit b = (BarbarianUnit) EntityRegistry.getEntity("barbarian");
		b.setGridPosition(getGrid().getPositionOf(this));
		LevelHandler.getCurrentLevel().addEntity(b);
	}

}
