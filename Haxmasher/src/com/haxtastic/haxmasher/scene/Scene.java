package com.haxtastic.haxmasher.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haxtastic.haxmasher.Haxput;
import com.haxtastic.haxmasher.entity.Actor;
import com.haxtastic.haxmasher.screen.Screen;

public abstract class Scene {
	protected List<Actor> actors = new ArrayList<Actor>();
	protected Screen screen;
	
	public void init(Screen s) {
		screen = s;
		actors.clear();
	}
	
	public abstract void act(float dt, Haxput input);
	public abstract void draw(float dt, SpriteBatch batch, BitmapFont font);
	public void removed() {
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public Actor addActor(Actor act) {
		actors.add(act);
		act.init();
		return act;
	}
	
	public void cleanActors() {
		actors.clear();
	}
	public boolean removeActor(Actor act) {
		return actors.remove(act);
	}
	
	public void actActors(float dt) {
		Iterator<Actor> iter = getActors().iterator();
		while(iter.hasNext()){
		    Actor actor = iter.next();
		    actor.act(dt);
		}
	}
}