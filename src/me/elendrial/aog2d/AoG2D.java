package me.elendrial.aog2d;

import me.hii488.EngineSettings;
import me.hii488.controllers.GameController;

public class AoG2D {
	
	public static void main(String[] args) {
		Initialisation.setup();
		
		EngineSettings.Texture.tileSize = 16;
		
		GameController.loadAndStartGame("SpaceInvaders", 1000, 800);
	}
	
}
