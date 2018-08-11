package me.elendrial.aog2d.objects.tiles.structure;

public class PortalTile extends StructureTile{

	public PortalTile() {
		this.canPassThrough = true;
		this.tileName = "portal";
	}
	
	@Override
	public void onClick() {
		// TODO: Bring up summon menu, this is temp
		System.out.println("summon");
	}

}
