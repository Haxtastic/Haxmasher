package com.haxtastic.haxmasher.entity.ui;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.haxtastic.haxmasher.Art;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.Constants.Buttons;
import com.haxtastic.haxmasher.entity.Actor;


public class Button extends Actor {
	public static class States {
		public static enum state {
			normal, hover, clicked, clicking, blocked;
		}
		public static HashMap<States.state, String> states = new HashMap<States.state, String>(){
			private static final long serialVersionUID = 1L;
		{
	        put(States.state.normal,"button");
	        put(States.state.hover,"buttonhover");
	        put(States.state.clicked,"buttonclicked");
	        put(States.state.clicking,"buttonclicked");
	        put(States.state.blocked,"buttonblocked");
	    }};
	}
	private States.state state;
	public String text;
	public boolean[] pushed = new boolean[3];
	public Buttons.type type;
	
	public Button(String txt, Buttons.type t, float bx, float by, float bw, float bh) {
		super(States.states.get(States.state.normal));
		text = txt;
		type = t;
		x = bx;
		y = by;
		width = bw;
		height = bh;
		setState(States.state.normal);
	}
	
	public void reset() {
		pushed[0] = pushed[1] = pushed[2] = false;
	}
	
	public States.state setState(States.state s) {
		state = s;
		return state;
	}
	
	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		AtlasRegion spriteRegion = null;
		switch(getState()){
			case normal:
				spriteRegion = Art.regions.get(States.states.get(States.state.normal));
				break;
			case hover:
				spriteRegion = Art.regions.get(States.states.get(States.state.hover));
				break;
			case clicked:
			case clicking:
				spriteRegion = Art.regions.get(States.states.get(States.state.clicked));
				break;
			case blocked:
				spriteRegion = Art.regions.get(States.states.get(States.state.blocked));
				break;
			default:
				spriteRegion = Art.regions.get(States.states.get(States.state.normal));
				break;
		}
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
		String msg = text;
		posX = (x+(width/2))*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = (y+(height/2))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height/2);
		font.draw(batch, msg, posX, posY);
	}
	
	public boolean isState(States.state s) {
		return getState() == s; 
	}

	public States.state getState() {
		return state;
	}
}
