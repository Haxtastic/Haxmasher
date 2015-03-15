/*
Copyright 2014 Magnus Bridén

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.haxtastic.haxmasher.entity;

import com.haxtastic.haxmasher.Constants;

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