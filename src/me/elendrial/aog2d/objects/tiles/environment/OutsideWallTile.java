package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;

public class OutsideWallTile extends AoGTile{
	
	public OutsideWallTile() {
		this.canPassThrough = false;
		this.tileName = "outsideWall";
	}
	
}
