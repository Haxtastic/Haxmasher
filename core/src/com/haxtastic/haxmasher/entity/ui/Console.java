package com.haxtastic.haxmasher.entity.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.haxtastic.haxmasher.Art;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.Actor;


public class Console extends Actor {
	private List<String> lines = new ArrayList<String>();
	private int maxLines = 8;
	private float linex = 7.15f;
	private float liney = 0.45f;
	private float space = 0.1f;
	
	public Console() {
		super("console");
		x = Constants.Positions.consoleX;
		y = Constants.Positions.consoleY;
		width = Constants.Sizes.consoleX;;
		height = Constants.Sizes.consoleY;
	}
	
	public void log(String text) {
		if(lines.size() > maxLines)
			lines.remove(0);
		lines.add(text);
	}
	
	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		AtlasRegion spriteRegion =  Art.regions.get(name);
		if(width == 0)
			width = spriteRegion.getRegionWidth();
		if(height == 0)
			height = spriteRegion.getRegionHeight();
		if(width < 0) width = -width;
		float posX = x * Constants.PIXELS_PER_METER_X;
		float posY = y * Constants.PIXELS_PER_METER_Y;
		batch.draw(spriteRegion, posX, posY, width * Constants.PIXELS_PER_METER_X, height * Constants.PIXELS_PER_METER_Y);
		//Draw text
		font.setColor(0, 0, 0, 1);
		float sX = font.getScaleX();
		float sY = font.getScaleY();
		font.setScale(1.1f, 1.1f);
		String msg = null;
		int i = 0;
		ListIterator<String> iter = lines.listIterator(lines.size());
		while(iter.hasPrevious()){
			msg = iter.previous();
			posX = linex * Constants.PIXELS_PER_METER_X;
			posY = (liney + (space*i)) * Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height*i);
			font.draw(batch, msg, posX, posY);
			i++;
		}
		font.setScale(sX, sY);
	}
}
