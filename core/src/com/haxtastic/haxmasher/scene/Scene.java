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