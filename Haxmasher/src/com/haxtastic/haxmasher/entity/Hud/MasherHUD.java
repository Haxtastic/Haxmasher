package com.haxtastic.haxmasher.entity.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.haxtastic.haxmasher.Art;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.Actor;
import com.haxtastic.haxmasher.entity.PlayerStats;
import com.haxtastic.haxmasher.entity.masher.Guy;
import com.haxtastic.haxmasher.entity.masher.Masher;
import com.haxtastic.haxmasher.entity.masher.Player;

public class MasherHUD extends Hud {
	
	public Actor hpslice;
	public float bars, prevbars;
	private float space = 1.5f;
	private float statsX;
	private float statsY;
	public float dmg = 1;
	public String type;
	public Masher masher;
	
	public MasherHUD(Masher mash) {
		super("hpframe");
		hpslice = new Actor("hpbar");
		masher = mash;
		if(mash.getClass().equals(Player.class)) {
			x = Constants.Positions.playerHPBarX;
			y = Constants.Positions.playerHPBarY;
			hpslice.x = Constants.Positions.playerHPBarX+(3.01f/Constants.PIXELS_PER_METER_X);
			hpslice.y = Constants.Positions.playerHPBarY+(3/Constants.PIXELS_PER_METER_Y);
			statsX = Constants.Positions.playerStatsX*Constants.PIXELS_PER_METER_X;
			statsY = Constants.Positions.playerStatsY*Constants.PIXELS_PER_METER_Y;
		} else if(mash.getClass().equals(Guy.class)) {
			x = Constants.Positions.enemyHPBarX;
			y = Constants.Positions.enemyHPBarY;
			hpslice.x = Constants.Positions.enemyHPBarX+(3.01f/Constants.PIXELS_PER_METER_X);
			hpslice.y = Constants.Positions.enemyHPBarY+(3/Constants.PIXELS_PER_METER_Y);
			statsX = Constants.Positions.enemyStatsX*Constants.PIXELS_PER_METER_X;
			statsY = Constants.Positions.enemyStatsY*Constants.PIXELS_PER_METER_Y;
		}
		width = Constants.Sizes.hpbarX;
		height = Constants.Sizes.hpbarY;
		hpslice.width = Constants.Sizes.hpsliceX;
		hpslice.height = Constants.Sizes.hpsliceY;
		prevbars = bars = 1f;
	}
	
	@Override
	public void act(float dt) {
		bars = (float)masher.curhealth/(float)masher.maxhealth;
		prevbars = (float)masher.health/(float)masher.maxhealth;
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
		spriteRegion = Art.regions.get(hpslice.name);
		if(hpslice.width == 0)
			hpslice.width = spriteRegion.getRegionWidth();
		if(hpslice.height == 0)
			hpslice.height = spriteRegion.getRegionHeight();
		if(hpslice.width < 0) hpslice.width = -hpslice.width;
		/*for(float i = 0; i < bars*100; i++) {
			posX = (bar.x + (0.04f*i)) * Constants.PIXELS_PER_METER_X;
			posY = bar.y * Constants.PIXELS_PER_METER_Y;
			batch.draw(spriteRegion, posX, posY, bar.width * Constants.PIXELS_PER_METER_X, bar.height * Constants.PIXELS_PER_METER_Y);
		}*/
		posX = hpslice.x * Constants.PIXELS_PER_METER_X;
		posY = hpslice.y * Constants.PIXELS_PER_METER_Y;
		float tempwidth = hpslice.width*(prevbars*100);
		batch.draw(spriteRegion, posX, posY, tempwidth * Constants.PIXELS_PER_METER_X, hpslice.height * Constants.PIXELS_PER_METER_Y);
		
		//Draw text
		font.setColor(0, 0, 0, 1);
		String msg = (int)masher.health + "/" + (int)masher.maxhealth;
		posX = (x+(width/2))*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = (y+(height/2))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height/2);
		font.draw(batch, msg, posX, posY);
		
		posX = statsX;
		posY = statsY;
		msg = "Name: " + masher.nick;
		font.draw(batch, msg, posX, posY);
		
		posY-=(font.getBounds(msg).height*space);
		msg = "Level: " + masher.level;
		font.draw(batch, msg, posX, posY);
		
		msg = "Armor: " + (int)masher.armor + "(" + (int)((masher.armor/(Constants.Stats.armorReduction+masher.armor))*100) + "%)";
		posY-=(font.getBounds(msg).height*space);
		font.draw(batch, msg, posX, posY);
		msg = "Damage: " + (int)(masher.getDamage()*Constants.Stats.minDamage) + " - " + (int)(masher.getDamage()*Constants.Stats.maxDamage) + "(" + masher.crit + "%)";
		posY-=(font.getBounds(msg).height*space);
		font.draw(batch, msg, posX, posY);
		if(masher.getClass().equals(Player.class)) {
			int exp = masher.getExp();
			int nxp = masher.expForLevel(masher.getLevel()+1);
			msg = "exp: " + exp + "/" + nxp + " (" + (nxp-exp) + " left)";
			posY-=(font.getBounds(msg).height*space);
			font.draw(batch, msg, posX, posY);
		} else if(masher.getClass().equals(Guy.class)) {
			msg = "experience worth: " + masher.getExp();
			posY-=(font.getBounds(msg).height*space);
			font.draw(batch, msg, posX, posY);
		}
		//font.setScale(1f, 1f);
	}
	/*
	public void drawPlayer() {
		
	}
	
public void drawMasher() {
	//Draw text
	font.setColor(0, 0, 0, 1);
	String msg = (int)player.health + "/" + (int)player.maxhealth;
	posX = (x+(width/2))*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
	posY = (y+(height/2))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height/2);
	font.draw(batch, msg, posX, posY);
	
	posX = statsX;
	posY = statsY;
	
	msg = "Name: " + player.nick;
	font.draw(batch, msg, posX, posY);
	
	posY-=(font.getBounds(msg).height+space);
	if(type == "player")
		msg = "Level: " + PlayerStats.getLevel();
	else
		msg = "Level: " + player.level;
	font.draw(batch, msg, posX, posY);
	
	msg = "Armor: " + (int)player.armor + "(" + (int)((player.armor/(Constants.Stats.armorReduction+player.armor))*100) + "%)";
	posY-=(font.getBounds(msg).height+space);
	font.draw(batch, msg, posX, posY);
	msg = "Damage: " + (int)(player.getDamage()*Constants.Stats.minDamage) + " - " + (int)(player.getDamage()*Constants.Stats.maxDamage);
	posY-=(font.getBounds(msg).height+space);
	font.draw(batch, msg, posX, posY);
	}*/
}
