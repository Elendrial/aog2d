package me.elendrial.aog2d.gameSystems;

import com.sun.glass.events.KeyEvent;

import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.Camera;
import me.hii488.interfaces.ITickable;
import me.hii488.registries.KeyBindRegistry;

// TODO: Come up with a better name
public class KeyController implements ITickable{
	
	private static KeyController kc;
	
	public static KeyController get() {
		return kc == null ? (kc = new KeyController()) : kc;
	}
	
	public static void init() {
		KeyBindRegistry.setKeyBind(KeyBind.MOVE_DOWN, KeyEvent.VK_S, KeyEvent.VK_DOWN);
		KeyBindRegistry.setKeyBind(KeyBind.MOVE_UP, KeyEvent.VK_W, KeyEvent.VK_UP);
		KeyBindRegistry.setKeyBind(KeyBind.MOVE_LEFT, KeyEvent.VK_A, KeyEvent.VK_LEFT);
		KeyBindRegistry.setKeyBind(KeyBind.MOVE_RIGHT, KeyEvent.VK_D, KeyEvent.VK_RIGHT);
	}

	@Override
	public void updateOnTick() {
		Vector cameraMovement = new Vector(0,0);
		if(KeyBindRegistry.isPressed("MOVE_LEFT")) 
			cameraMovement.translate(-1, 0);

		if(KeyBindRegistry.isPressed("MOVE_RIGHT")) 
			cameraMovement.translate(1, 0);
		
		if(KeyBindRegistry.isPressed("MOVE_UP"))
			cameraMovement.translate(0, -1);

		if(KeyBindRegistry.isPressed("MOVE_DOWN")) 
			cameraMovement.translate(0, 1);
		
		if(cameraMovement.isNotNothing()) {
			Camera.translate(cameraMovement);
		}
	}

	@Override
	public void updateOnSec() {}
	
}
