package me.elendrial.aog2d.gameSystems.gods;

import java.awt.Color;
import java.util.ArrayList;

import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.registries.TextureRegistry;

public class God {
	
	public String name;
	public Color color;
	public ArrayList<Class<? extends Unit>> units;
	
	public God(String name, Color color) {
		this.name = name;
		this.color = color;
		this.units = new ArrayList<Class<? extends Unit>>();
		
		TextureRegistry.addTexture("textures/gui/godIcons/" + name + ".png", name + "_icon", 0);
	}
	
	public void addUnit(Class<? extends Unit> unit) {
		units.add(unit);
	}
	
	public static God NEUTRAL = new God("neutral", Color.gray);
	public static God SARADOMIN = new God("saradomin", Color.blue);
	public static God ZAMORAK = new God("zamorak", Color.red);
	public static God GUTHIX = new God("guthix", Color.green);
	public static God SEREN = new God("seren", Color.cyan);
	public static God BANDOS = new God("bandos", Color.yellow);
	public static God MENAPHITE_PANTHEON = new God("menaphite pantheon", Color.orange);
	
}
