package me.elendrial.aog2d;

import me.hii488.controllers.InitialisationController;
import me.hii488.interfaces.IInitialiser;

public class Initialisation implements IInitialiser{

	public static void setup() {
		InitialisationController.addInitialiser(new Initialisation());
	}
	
	
	
	
	@Override
	public void preInit() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

}
