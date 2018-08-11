package me.elendrial.aog2d;

import me.hii488.EngineSettings;
import me.hii488.controllers.GameController;

public class AoG2D {
	
	public static void main(String[] args) {
		Initialisation.setup();
		
		// Temporary, probably should triple the size at least.
		EngineSettings.Texture.tileSize = 10;
		
		GameController.loadAndStartGame("SpaceInvaders", 1000, 800);
	}
	
}
