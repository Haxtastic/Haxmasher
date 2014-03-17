package com.haxtastic.haxmasher;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

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
	
	public class Positions {
		public static final float playerX = 1.5f;
		public static final float playerY = 4.0f;
		
		public static final float enemyX = 12.5f;
		public static final float enemyY = 6.7f;
		
		public static final float playerHPBarX = 10.5f;
		public static final float playerHPBarY = 4.2f;
		
		public static final float enemyHPBarX = 1.5f;
		public static final float enemyHPBarY = 8.0f;
	}
	
	public static float PIXELS_PER_METER_X;
	public static float PIXELS_PER_METER_Y;
	
	public class Layers {
	}

}

