package com.haxtastic.haxmasher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.haxtastic.haxmasher.entity.Actor;
import com.haxtastic.haxmasher.entity.Hud.MasherHUD;
import com.haxtastic.haxmasher.entity.Masher.Guy;
import com.haxtastic.haxmasher.entity.Masher.Player;

public class Scene {
	public List<Actor> actors = new ArrayList<Actor>();
	public Player player;
	private final Random random = new Random(1000);
	
	public Scene() {
		Actor bg = new Actor("background");
		bg.width = 19.0f;
		bg.height= 10.f;
		addActor(bg);
		
		player = new Player("Hero");
		player.x = Constants.Positions.playerX;
		player.y = Constants.Positions.playerY;
		addActor(player);
		
		Guy act = new Guy("Fredrik");
		act.x = Constants.Positions.enemyX;
		act.y = Constants.Positions.enemyY;
		addActor(act);
		
		Actor hpBar = new MasherHUD("player", player);
		addActor(hpBar);
		Actor mashBar = new MasherHUD("masher", act);
		addActor(mashBar);
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public Actor addActor(Actor act) {
		actors.add(act);
		act.init();
		return act;
	}
}
