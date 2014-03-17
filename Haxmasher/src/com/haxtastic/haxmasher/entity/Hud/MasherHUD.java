package com.haxtastic.haxmasher.entity.Hud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.haxtastic.haxmasher.Art;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.Actor;
import com.haxtastic.haxmasher.entity.Masher.Masher;

public class MasherHUD extends Hud {
	
	public Actor bar;
	public float bars;
	public float time = 0;
	public float dmg = 1;
	public String type;
	public Masher player;
	
	public MasherHUD(String type, Masher play) {
		super("hpframe");
		player = play;
		this.type = type;
		bar = new Actor("hpbar");
		if(type == "player") {
			x = Constants.Positions.playerHPBarX;
			y = Constants.Positions.playerHPBarY;
			bar.x = Constants.Positions.playerHPBarX+(3.01f/Constants.PIXELS_PER_METER_X);
			bar.y = Constants.Positions.playerHPBarY+(3/Constants.PIXELS_PER_METER_Y);
		}else if(type == "masher") {
			x = Constants.Positions.enemyHPBarX;
			y = Constants.Positions.enemyHPBarY;
			bar.x = Constants.Positions.enemyHPBarX+(3.01f/Constants.PIXELS_PER_METER_X);
			bar.y = Constants.Positions.enemyHPBarY+(3/Constants.PIXELS_PER_METER_Y);
		}
		width = 3.8f;
		height = 0.55f;
		bar.width = 0.0375f;
		bar.height = 0.5f;
	}
	
	@Override
	public void act(float dt) {
		bars = player.health/player.maxhealth;
		
		time+=dt;
		if(time > 1f) {
			time = 0;
			if(player.health > 0)
				player.health-=1;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		
		//Draw hpframe
		AtlasRegion spriteRegion =  Art.regions.get(name);
		if(width == 0)
			width = spriteRegion.getRegionWidth();
		if(height == 0)
			height = spriteRegion.getRegionHeight();
		if(width < 0) width = -width;
		float posX = x * Constants.PIXELS_PER_METER_X;
		float posY = y * Constants.PIXELS_PER_METER_Y;
		batch.draw(spriteRegion, posX, posY, width * Constants.PIXELS_PER_METER_X, height * Constants.PIXELS_PER_METER_Y);
		
		//Draw health
		spriteRegion = Art.regions.get(bar.name);
		if(bar.width == 0)
			bar.width = spriteRegion.getRegionWidth();
		if(bar.height == 0)
			bar.height = spriteRegion.getRegionHeight();
		if(bar.width < 0) bar.width = -bar.width;
		/*for(float i = 0; i < bars*100; i++) {
			posX = (bar.x + (0.04f*i)) * Constants.PIXELS_PER_METER_X;
			posY = bar.y * Constants.PIXELS_PER_METER_Y;
			batch.draw(spriteRegion, posX, posY, bar.width * Constants.PIXELS_PER_METER_X, bar.height * Constants.PIXELS_PER_METER_Y);
		}*/
		posX = bar.x * Constants.PIXELS_PER_METER_X;
		posY = bar.y * Constants.PIXELS_PER_METER_Y;
		float tempwidth = bar.width*(bars*100);
		batch.draw(spriteRegion, posX, posY, tempwidth * Constants.PIXELS_PER_METER_X, bar.height * Constants.PIXELS_PER_METER_Y);
		
		//Draw text
		font.setColor(0, 0, 0, 1);
		String msg = (int)player.health + "/" + (int)player.maxhealth;
		if(type == "player") {
			posX = (x+(width/2))*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
			posY = (y+(height/2))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height/2);
		}else if(type == "masher") {
			posX = (x+(width/2))*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
			posY = (y+(height/2))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height/2);
		}
		font.draw(batch, msg, posX, posY);
		
		msg = "Level: " + player.level;
		if(type == "player") {
			posX = (x*Constants.PIXELS_PER_METER_X);
			posY = (y+(height+0.05f))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
		}else if(type == "masher") {
			posX = (x*Constants.PIXELS_PER_METER_X);
			posY = (y+(height+0.05f))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
			//posX = (x*Constants.PIXELS_PER_METER_X);
			//posY = ((y-0.05f)*Constants.PIXELS_PER_METER_Y);
		}
		font.draw(batch, msg, posX, posY);
	}
}
