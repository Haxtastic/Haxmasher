package com.haxtastic.haxmasher.entity;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.haxtastic.haxmasher.Art;
import com.haxtastic.haxmasher.Constants;


public class Actor extends Entity {
	public String name;
	public void init() {} 
	
	public Actor(String nme) {
		super(0, 0 ,0, 0);
		name = nme;
	}
	
	public void act(float dt) {
		
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		AtlasRegion spriteRegion =  Art.regions.get(name);
		if(width == 0)
			width = spriteRegion.getRegionWidth();
		if(height == 0)
			height = spriteRegion.getRegionHeight();
		if(width < 0) width = -width;
		float posX = x * Constants.PIXELS_PER_METER_X;
		float posY = y * Constants.PIXELS_PER_METER_Y;
		batch.draw(spriteRegion, posX, posY, width * Constants.PIXELS_PER_METER_X, height* Constants.PIXELS_PER_METER_Y);
	}
}
