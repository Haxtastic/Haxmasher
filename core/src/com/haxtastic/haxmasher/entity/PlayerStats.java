package com.haxtastic.haxmasher.entity;

public class PlayerStats {
	public static int	 	exp;
	public static int   	level;
	public static int		points;
	public static int		pDamage, pHealth, pArmor, pCrit;
	
	public PlayerStats() {
	}
	public static int getExp() {
		return exp;
	}
	
	public static float addExp(int xp) {
		return setExp(exp+xp);
	}
	
	public static float setExp(int xp) {
		exp = xp;
		return exp;
	}
	
	public static int expForLevel(int lvl){
		return Math.round((float)(50.0f/3.0f*(Math.pow(lvl, 3)-(6*Math.pow(lvl, 2))+(17*lvl)-12)));
	}
	
	public static boolean canLevel() {
		return expForLevel(level+1) <= exp;
	}
	
	public static int checkLevel() {
		int lvl = 0;
		while(expForLevel(lvl+level+1) <= exp) {
			lvl++;
		}
		level+=lvl;
		return lvl;
	}
	
	public static void resetStats() {
		level = pDamage = pHealth = pArmor = pCrit = 0;
	}
}