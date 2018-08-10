package me.elendrial.aog2d.objects.tiles.structure;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.turns.IUpdating;
import me.elendrial.aog2d.objects.tiles.AoGTile;

public abstract class StructureTile extends AoGTile implements IUpdating {

	public int captureTime;
	public int captureAmount;
	public int manaPerTurn;
	public Player capturedBy;
	public Player progressingCapturer;
	
	public void onTurnStart() {
		if(captureAmount < captureTime) {
			//	if barbarian of progressingCapturer is on, increase capture amount by 15
			//	if barbarian of another player, decrease capture amount by 15, wrap into their capture bar.
		}
		
		if(capturedBy != null) {
			capturedBy.mana += manaPerTurn;
		}
		
	}

}
