package me.elendrial.aog2d.objects.tiles.environment;

import me.elendrial.aog2d.objects.tiles.AoGTile;

public class OpenTile extends AoGTile {

	public OpenTile() {
		this.canPassThrough = true;
		this.tileName = "open";
	}

}
