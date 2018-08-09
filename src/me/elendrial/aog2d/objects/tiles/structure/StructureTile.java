package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.elendrial.aog2d.objects.tiles.AoGTile;

public abstract class StructureTile extends AoGTile implements IUpdating {

	public int captureTime;
	public int captureAmount;
	public int capturedBy;
	
	

}
