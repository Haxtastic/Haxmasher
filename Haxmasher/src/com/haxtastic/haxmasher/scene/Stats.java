package com.haxtastic.haxmasher.scene;

import java.util.Iterator;
import java.util.List;

import com.haxtastic.haxmasher.Art;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.Haxmasher;
import com.haxtastic.haxmasher.Haxput;
import com.haxtastic.haxmasher.Constants.Buttons;
import com.haxtastic.haxmasher.Constants.state;
import com.haxtastic.haxmasher.entity.Actor;
import com.haxtastic.haxmasher.entity.PlayerStats;
import com.haxtastic.haxmasher.entity.masher.Player;
import com.haxtastic.haxmasher.entity.ui.Button;
import com.haxtastic.haxmasher.entity.ui.Menu;
import com.haxtastic.haxmasher.screen.GameScreen;
import com.haxtastic.haxmasher.screen.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Stats extends Scene {
	private int points, level = 0;
	private Menu menu;
	private boolean ready = false;
	private Scene parent = null;
	
	public Stats() {
	}
	
	public Stats(Scene par) {
		parent = par;
	}
	
	@Override
	public void init(Screen s) {
		super.init(s);
		level = PlayerStats.checkLevel();
		points = level*Constants.Stats.levelWeight;
		if(points <= 0)
			((GameScreen)screen).setScene(new Battle());
		Actor bg = new Actor("statsground");
		if(parent == null) {
			bg.width = 16.0f;
			bg.height= 9.0f;
		} else {
			bg.x = 1f;
			bg.y = 2f;
			bg.height = 6f;
			bg.width = 14f;
		}
		addActor(bg);
		menu = new Menu();
		addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.health), Buttons.type.health, Buttons.healthX, Buttons.healthY, Buttons.healthWidth, Buttons.healthHeight));
		addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.damage), Buttons.type.damage, Buttons.damageX, Buttons.damageY, Buttons.damageWidth, Buttons.damageHeight));
		addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.armor), Buttons.type.armor, Buttons.armorX, Buttons.armorY, Buttons.armorWidth, Buttons.armorHeight));
		addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.crit), Buttons.type.crit, Buttons.critX, Buttons.critY, Buttons.critWidth, Buttons.critHeight));
	}
	
	@Override
	public void act(float dt, Haxput input) {
		actActors(dt);
		List<Button> buttons = menu.handleButtons(dt, input);
		Iterator<Button> bIter = buttons.iterator();
		int point = 1;
		while(bIter.hasNext()){
		    Button button = bIter.next();
		    if(button.pushed[Input.Buttons.LEFT]) {
		    	if(input.buttons[Haxput.CTRL] && input.buttons[Haxput.SHIFT])
		    		if(points >= 100)
		    			point = 100;
		    		else
		    			point = points;
		    	else if(input.buttons[Haxput.CTRL] && !input.buttons[Haxput.SHIFT])
		    		if(points >= 5)
			    		point = 5;
			    	else
			    		point = points;
		    	switch(button.type) {
		    		case start:
		    			((GameScreen)screen).setScene(new Battle());
		    			break;
					case armor:
						points-=point;
				    	PlayerStats.pArmor+=point;
						break;
					case crit:
						points-=point;
				    	PlayerStats.pCrit+=point;
						break;
					case damage:
						points-=point;
				    	PlayerStats.pDamage+=point;
						break;
					case health:
						points-=point;
				    	PlayerStats.pHealth+=point;
						break;
					default:
						break;
		    	}
		    	menu.resetButtons(input);
		    }
			if(readyButtons())
				break;
		}
	}
	
	public boolean readyButtons() {
		if(points <= 0 && !menu.hasState(Button.States.state.blocked)) {
			//cleanActors();
			Iterator<Button> bIter = menu.buttons.iterator();
			while(bIter.hasNext()) {
			    Button button = bIter.next();
			    button.setState(Button.States.state.blocked);
			}
			addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.start), Buttons.type.start, Buttons.startX, Buttons.startY, Buttons.startWidth, Buttons.startHeight));
			return true;
		}
		return false;
	}
	@Override
	public void draw(float dt, SpriteBatch batch, BitmapFont font) {
		if(parent != null)
			parent.draw(dt, batch, font);
		Iterator<Actor> iter = getActors().iterator();
		while(iter.hasNext()){
		    Actor actor = iter.next();
		    actor.draw(batch, font);
		}
		font.setColor(0, 0, 0, 1);
		float sX = font.getScaleX();
		float sY = font.getScaleY();
		font.setScale(1.575f, 1.725f);
		
		String msg = "You have gained " + level;
		msg+=(level > 1 ? " levels." : " level.");
		float posX = (Constants.Positions.pointsTextX)*Constants.PIXELS_PER_METER_X + (font.getBounds(" to spend").width/2);
		float posY = (Constants.Positions.pointsTextY+0.1f)*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
		font.draw(batch, msg, posX, posY);
		
		msg = "You have " + points + " skill points to spend.";
		posX = Constants.Positions.pointsTextX*Constants.PIXELS_PER_METER_X;
		posY = Constants.Positions.pointsTextY*Constants.PIXELS_PER_METER_Y;
		font.draw(batch, msg, posX, posY);
		
		msg = Constants.Stats.healthWeight + " * " + (PlayerStats.pHealth+PlayerStats.level);
		posX = Constants.Buttons.healthTextX*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = Constants.Buttons.pointsY*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
		font.draw(batch, msg, posX, posY);
		
		msg = Constants.Stats.damageWeight + " * " + (PlayerStats.pDamage+PlayerStats.level);
		posX = Constants.Buttons.damageTextX*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = Constants.Buttons.pointsY*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
		font.draw(batch, msg, posX, posY);
		
		msg = Constants.Stats.armorWeight + " * " + (PlayerStats.pArmor+PlayerStats.level);
		posX = Constants.Buttons.armorTextX*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = Constants.Buttons.pointsY*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
		font.draw(batch, msg, posX, posY);
		
		msg = Constants.Stats.critWeight + " * " + (PlayerStats.pCrit+PlayerStats.level);
		posX = Constants.Buttons.critTextX*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = Constants.Buttons.pointsY*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height);
		font.draw(batch, msg, posX, posY);
		font.setScale(sX, sY);
	}
	/*@Override
	public void draw(float dt) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if(parent != null)
			parent.render(dt, batch, font);
		AtlasRegion spriteRegion =  Art.regions.get("stats");
		if(width == 0)
			width = spriteRegion.getRegionWidth();
		if(height == 0)
			height = spriteRegion.getRegionHeight();
		if(width < 0) width = -width;
		float posX = (x-width/2);
		float posY = (y-height/2);
		batch.draw(spriteRegion, posX * Constants.PIXELS_PER_METER_X, posY * Constants.PIXELS_PER_METER_Y, width * Constants.PIXELS_PER_METER_X, height * Constants.PIXELS_PER_METER_Y);
		font.setColor(0, 0, 0, 1);
		String msg = "Click to continue";
		posX = (posX+(width/2))*Constants.PIXELS_PER_METER_X - (font.getBounds(msg).width/2);
		posY = (posY+(height/2))*Constants.PIXELS_PER_METER_Y + (font.getBounds(msg).height/2);
		font.draw(batch, msg, posX, posY);
		batch.end();
	}*/

}

