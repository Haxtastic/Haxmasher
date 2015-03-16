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
package com.haxtastic.haxmasher.scene;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haxtastic.haxmasher.Constants.Buttons;
import com.haxtastic.haxmasher.Constants.state;
import com.haxtastic.haxmasher.Haxput;
import com.haxtastic.haxmasher.entity.Actor;
import com.haxtastic.haxmasher.entity.EntityFactory;
import com.haxtastic.haxmasher.entity.PlayerStats;
import com.haxtastic.haxmasher.entity.hud.MasherHUD;
import com.haxtastic.haxmasher.entity.masher.Guy;
import com.haxtastic.haxmasher.entity.masher.Masher;
import com.haxtastic.haxmasher.entity.masher.Player;
import com.haxtastic.haxmasher.entity.ui.Button;
import com.haxtastic.haxmasher.entity.ui.Console;
import com.haxtastic.haxmasher.entity.ui.Menu;
import com.haxtastic.haxmasher.screen.GameScreen;
import com.haxtastic.haxmasher.screen.Screen;

public class Battle extends Scene {
	private Player player = null;
	private Guy enemy = null;
	private Menu menu = null;
	//private MasherHUD pHud, eHud = null;
	private state State;
	private float attackDelay = 0f;
	private Console console = null;
	
	public Battle() {
	}
	
	@Override
	public void init(Screen s) {
		super.init(s);
		Actor bg	= new Actor("background");
		bg.width	= 16.0f;
		bg.height	= 9.f;
		addActor(bg);
		
		State = state.input;
		
		player = (Player)addActor(EntityFactory.createPlayer());
		
		enemy = (Guy)addActor(EntityFactory.createGuy(player.getLevel()));
		
		//pHud = (MasherHUD)
		addActor(new MasherHUD(player));
		//eHud = (MasherHUD)
		addActor(new MasherHUD(enemy));
		
		if(menu == null)
			menu = new Menu();
		menu.clean();
		addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.attack), Buttons.type.attack, Buttons.attackX, Buttons.attackY, Buttons.attackWidth, Buttons.attackHeight));
		addActor(menu.addButton(Buttons.hashButtons.get(Buttons.type.reset), Buttons.type.reset, Buttons.resetX, Buttons.resetY, Buttons.resetWidth, Buttons.resetHeight));
		
		if(console == null)
			console = new Console();
		addActor(console);
		console.log("A wild " + enemy.nick + " appears.");
	}
	
	@Override
	public void act(float dt, Haxput input) {
		actActors(dt);
		checkHealth();
		List<Button> buttons = getMenu().handleButtons(dt, input);
		switch(State) {
			case input:
				Iterator<Button> bIter = buttons.iterator();
				while(bIter.hasNext()){
				    Button button = bIter.next();
				    if(button.pushed[Input.Buttons.LEFT] && button.type == Buttons.type.attack) {
				    	State = state.attack;
				    	getMenu().resetButtons(input);
				    } else if(button.pushed[Input.Buttons.LEFT] && button.type == Buttons.type.reset) {
				    	PlayerStats.resetStats();
				    	((GameScreen)screen).setScene(new Stats());
				    }
				}
				break;
			case attack:
				if(getPlayer().health == getPlayer().curhealth) {
					console.log(player.attack(getEnemy()));
					State = state.counter;
					getMenu().resetButtons(input);
				}
				break;
			case counter:
				if(getEnemy().health == getEnemy().curhealth) {
					if(attackDelay > 0.5f) {
						console.log(getEnemy().attack(player));
						State = state.input;
						attackDelay = 0f;
						getMenu().resetButtons(input);
						break;
					}
					attackDelay+=dt;
				}
				break;
			default:
				break;
		}
	}
	
	@Override
	public void draw(float dt, SpriteBatch batch, BitmapFont font) {
		//font.setScale(1.15f, 1.17f);
		Iterator<Actor> iter = getActors().iterator();
		while(iter.hasNext()){
		    Actor actor = iter.next();
		    actor.draw(batch, font);
		}
		//font.setScale(1f, 1f);
		/*font.setColor(0, 0, 0, 1);
		String msg = "HP percent: " + (float)((float)getPlayer().curhealth/(float)getPlayer().maxhealth)/100f;
		font.draw(batch, msg, 0.1f*Constants.PIXELS_PER_METER_X, 0.50f*Constants.PIXELS_PER_METER_Y);
		float hpthing = (float)((((float)getPlayer().health-(float)getPlayer().curhealth)/(float)getPlayer().maxhealth));
		msg = "HP remove: " + hpthing;
		font.draw(batch, msg, 0.1f*Constants.PIXELS_PER_METER_X, 0.80f*Constants.PIXELS_PER_METER_Y);
		msg = "Prevbars: " + pHud.prevbars;
		font.draw(batch, msg, 0.1f*Constants.PIXELS_PER_METER_X, 1.1f*Constants.PIXELS_PER_METER_Y);
		msg = "HP thing: " + (pHud.bars);
		font.draw(batch, msg, 0.1f*Constants.PIXELS_PER_METER_X, 1.4f*Constants.PIXELS_PER_METER_Y);*/
	}
	
	public void checkHealth() {
		if(player.health == 0) {
			console.log("You have died.");
			((GameScreen)screen).setScene(this);
			State = state.input;
		} else if(getEnemy().health == 0) {
			console.log(getEnemy().getNick() + " has died.");
			console.log("You have gained " + enemy.exp + " experience.");
			PlayerStats.addExp(enemy.exp);
			if(PlayerStats.canLevel())
				((GameScreen)screen).setScene(new Stats(this));
			else
				((GameScreen)screen).setScene(this);
			State = state.input;
		}
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Masher getEnemy() {
		return enemy;
	}
}
