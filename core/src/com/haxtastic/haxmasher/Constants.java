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
package com.haxtastic.haxmasher;

import java.util.HashMap;

public class Constants {
	
	public class Groups {
	}
	
	
	
	public static class Elements {
		
		public static enum type {
			Fire, Earth, Water, Lightning;
		}
		public static HashMap<Elements.type, String> elements = new HashMap<Elements.type, String>(){
			private static final long serialVersionUID = 1L;

		{
	        put(Elements.type.Fire,"Fire");
	        put(Elements.type.Earth,"Earth");
	        put(Elements.type.Water,"Water");
	        put(Elements.type.Lightning,"Lightning");
	    }};
	}
	
	public class Stats {
		public static final float 	minDamage 		= 0.8f;
		public static final float 	maxDamage 		= 1.2f;
		
		public static final float 	minWDamage 		= 0.5f;
		public static final float 	maxWDamage 		= 1.0f;
		
		public static final float	armorReduction 	= 50;
		public static final float	wArmorReduction = 50;
		
		public static final int		healthWeight	= 30;
		public static final int		damageWeight	= 6;
		public static final int		armorWeight		= 3;
		public static final int		critWeight		= 2;
		public static final int		weaponWeight	= 5;
		
		public static final int		levelWeight		= 10;
	}
	
	public static class Buttons {
		
		public static final float attackX = 0.15f;
		public static final float attackY = 1.5f;
		public static final float attackWidth = 3f;
		public static final float attackHeight = 1.5f;
		
		public static final float resetX = 3.30f;
		public static final float resetY = 1.5f;
		public static final float resetWidth = 3f;
		public static final float resetHeight = 1.5f;
		
		public static final float healthX = 1.55f;
		public static final float healthY = 2.5f;
		public static final float healthWidth = 3f;
		public static final float healthHeight = 1.5f;
		public static final float healthTextX = healthX+healthWidth/2;
		
		public static final float damageX = healthX+(healthWidth+0.2f);
		public static final float damageY = healthY;
		public static final float damageWidth = 3f;
		public static final float damageHeight = 1.5f;
		public static final float damageTextX = damageX+damageWidth/2;
		
		public static final float armorX = healthX+((healthWidth+0.2f)*2);
		public static final float armorY = healthY;
		public static final float armorWidth = 3f;
		public static final float armorHeight = 1.5f;
		public static final float armorTextX = armorX+armorWidth/2;
		
		public static final float critX = healthX+((healthWidth+0.2f)*3);
		public static final float critY = healthY;
		public static final float critWidth = 3f;
		public static final float critHeight = 1.5f;
		public static final float critTextX = critX+critWidth/2;
		
		public static final float startY = 9f/2f;
		public static final float startWidth = 3f;
		public static final float startHeight = 1.5f;
		public static final float startX = (damageX+(damageWidth/2)+0.1f);//(16f/2f)-startWidth;
		
		public static final float pointsY = healthY+healthHeight+0.05f;
		
		public static enum type {
			attack, inventory, health, damage, armor, crit, start, reset;
		}
		public static HashMap<Buttons.type, String> hashButtons = new HashMap<Buttons.type, String>(){
			private static final long serialVersionUID = 1L;

		{
	        put(Buttons.type.attack,"Attack");
	        put(Buttons.type.health,"health");
	        put(Buttons.type.damage,"damage");
	        put(Buttons.type.armor, "armor");
	        put(Buttons.type.crit, "crit");
	        put(Buttons.type.start, "Ready");
	        put(Buttons.type.reset, "Reset stats");
	    }};
	    
	    
	}
	
	public static enum state {
		input, attack, counter, checkPlayer, checkEnemy, reset;
	}
	
	public class Positions {
		public static final float playerX = 0.2f;
		public static final float playerY = 3.8f;
		public static final float enemyX = 13.8f;
		public static final float enemyY = 7.0f;
		
		public static final float playerHPBarX = playerX+Sizes.masherX+0.1f;
		public static final float playerHPBarY = playerY;
		public static final float enemyHPBarX = enemyX-Sizes.hpbarX;
		public static final float enemyHPBarY = enemyY+Sizes.masherY-Sizes.hpbarY-0.1f;
		
		
		
		public static final float consoleX = 6.95f;
		public static final float consoleY = 0.03f;
		
		public static final float playerStatsX = playerX+Sizes.masherX+0.1f;
		public static final float playerStatsY = playerY+(Sizes.masherY*1.2f);
		public static final float enemyStatsX = enemyHPBarX;
		public static final float enemyStatsY = enemyHPBarY-(0.1f*1.2f);
		
		public static final float pointsTextX = 3.5f;
		public static final float pointsTextY = 6.5f;
	}
	
	public class Sizes {
		public static final float masherX = 2.0f;
		public static final float masherY = 2.0f;
		
		public static final float hpbarX = 3.8f;
		public static final float hpbarY = 0.55f;
		
		public static final float hpsliceX = 0.0375f;
		public static final float hpsliceY = 0.5f;
		
		public static final float consoleX = 9f;
		public static final float consoleY = 3f;
	}
	
	public static float PIXELS_PER_METER_X;
	public static float PIXELS_PER_METER_Y;
	public static float SCREEN_MUL_X;
	public static float SCREEN_MUL_Y;
	
	public class Layers {
	}

}

