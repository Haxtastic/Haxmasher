package com.haxtastic.haxmasher.entity;


public class EnemyStats {
	private static int	exp;
	private static int	level;
	
	public static int getExp() {
		return exp;
	}
	
	public static int getLevel() {
		return level;
	}
	
	public static int addExp(int e) {
		return setExp(exp+e);
	}

	public static int setExp(int e) {
		exp = e;
		return exp;
	}
	
	public static int setLevel(int lvl) {
		exp = lvl;
		return exp;
	}
}