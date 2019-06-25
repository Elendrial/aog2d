package me.elendrial.aog2d.gameSystems;

import java.util.HashMap;

public enum UnitType {
	
	SKIRMISH, WARRIOR, RANGER, MAGE, HELPER, FLYING, CREEPER, TITAN;
	
	public static void init(){
		SKIRMISH.setStrengths(new UnitType[] {WARRIOR, FLYING, CREEPER}, new float[] {0.25f, 0.25F, 0.9F});
		WARRIOR.setStrengths(new UnitType[] {SKIRMISH, RANGER, FLYING, CREEPER}, new float[] {0.9F, 0.9F, 0.25F, 0.9F});
		RANGER.setStrengths(new UnitType[] {WARRIOR, MAGE, FLYING, CREEPER}, new float[] {0.25F, 0.9F, 0.9F, 0.9F});
		MAGE.setStrengths(new UnitType[] {WARRIOR, MAGE, FLYING, CREEPER}, new float[] {0.9F, 0.25F, 0.9F, 0.9F});
		HELPER.setStrengths(new UnitType[] {}, new float[] {});
		FLYING.setStrengths(new UnitType[] {SKIRMISH, WARRIOR, MAGE, CREEPER}, new float[] {0.9F, 0.9F, 0.9F, 0.9F});
		CREEPER.setStrengths(new UnitType[] {}, new float[] {});
		TITAN.setStrengths(new UnitType[] {SKIRMISH, WARRIOR, RANGER, MAGE, HELPER, FLYING, CREEPER, TITAN}, new float[] {0.9F, 0.9F, 0.9F, 0.9F, 0.9F, 0.9F, 0.9F, 0.9F});
	}
	
	public HashMap<UnitType, Float> strengths;
	
	private void setStrengths(UnitType[] u, float[] modifier) {
		HashMap<UnitType, Float> strengths = new HashMap<>();
		for(int i = 0; i < u.length; i++) {
			strengths.put(u[i], modifier[i]);
		}
		
		for(UnitType ut : UnitType.values()) {
			if(!strengths.containsKey(ut)) strengths.put(ut, 0.5f);
		}
		
		this.strengths = strengths;
	}
	
	public float getAttackModifier(UnitType u) {
		return strengths.get(u);
	}
	
}
