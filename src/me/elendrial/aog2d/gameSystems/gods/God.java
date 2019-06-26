package me.elendrial.aog2d.gameSystems.gods;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

import me.elendrial.aog2d.objects.units.Unit;
import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;
import me.hii488.registries.TextureRegistry;

public class God {
	
	public static ArrayList<God> gods = new ArrayList<God>();
	
	public String name;
	public Color color;
	public ArrayList<Class<? extends Unit>> units;
	
	public God(String name, Color color) {
		this.name = name;
		this.color = color;
		this.units = new ArrayList<Class<? extends Unit>>();
		gods.add(this);
		
		TextureRegistry.addTexture("textures/gui/godIcons/" + name + ".png", name + "_icon", 1);
	}
	
	public void addUnit(Class<? extends Unit> unit) {
		units.add(unit);
	}
	
	public static God unitAlignment(Unit u) {
		Class<? extends Unit> uc = Unit.class;
		for(God g : gods) {
			if(g.units.contains(uc)) return g;
		}
		
		Logger.getDefault().print(LogSeverity.ERROR, "No god for unit " + uc.getName());
		return null;
	}
	
	public static boolean isUnitAligned(Unit u, God g) {
		return unitAlignment(u).equals(g);
	}
	
	// Maybe make this an enum?
	public static God NEUTRAL = new God("neutral", Color.gray);
	public static God SARADOMIN = new God("saradomin", Color.blue);
	public static God ZAMORAK = new God("zamorak", Color.red);
	public static God GUTHIX = new God("guthix", Color.green);
	public static God SEREN = new God("seren", Color.cyan);
	public static God BANDOS = new God("bandos", Color.yellow);
	public static God MENAPHITE_PANTHEON = new God("menaphite pantheon", Color.orange);
	
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof God)) return false;
		if(!((God) o).color.equals(color)) return false;
		if(!((God) o).name.equals(name)) return false;
		if(!((God) o).units.equals(units)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, color, units);
	}
	
}
