package me.elendrial.aog2d.gameSystems.gods;

import java.awt.Color;

public class God {
	
	public String name;
	public Color color;
	// Units
	
	public God(String name, Color c) {
		this.name = name;
		color = c;
	}
	
	public static God NEUTRAL = new God("neutral", Color.gray);
	public static God SARADOMIN = new God("saradomin", Color.blue);
	public static God ZAMORAK = new God("zamorak", Color.red);
	public static God GUTHIX = new God("guthix", Color.green);
	public static God SEREN = new God("seren", Color.cyan);
	public static God BANDOS = new God("bandos", Color.yellow);
	public static God MENAPHITE_PANTHEON = new God("menaphite pantheon", Color.orange);
	
}
