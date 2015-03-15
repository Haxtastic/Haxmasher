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
package com.haxtastic.haxmasher;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.haxtastic.haxmasher.screen.GameScreen;
import com.haxtastic.haxmasher.screen.OverlayScreen;
import com.haxtastic.haxmasher.screen.Screen;

public class Haxmasher implements ApplicationListener {
	public static final int FRAME_WIDTH = 1920;
	public static final int FRAME_HEIGHT = 1080;
	
	private boolean running = true;
	private Screen screen;
	private Screen logo;
	
	private final Haxput input = new Haxput();
	
	private float accum = 0;
	private float dt = 1.0f / 45.0f;
	
	@Override
	public void create() {
		Constants.PIXELS_PER_METER_X = Haxmasher.FRAME_WIDTH/16;
		Constants.PIXELS_PER_METER_Y = Haxmasher.FRAME_HEIGHT/9;
		Constants.SCREEN_MUL_X = (float)Haxmasher.FRAME_WIDTH/Gdx.graphics.getWidth();
		Constants.SCREEN_MUL_Y = (float)Haxmasher.FRAME_HEIGHT/Gdx.graphics.getHeight();
		Art.load();
		setScreen(new GameScreen());
		logo = new OverlayScreen(this);
		logo.init(this);
		Gdx.input.setInputProcessor(input);
	}
	
	@Override
	public void render() {
		if(running) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			float delta = Gdx.graphics.getDeltaTime();
			
			if(delta > 0.45f)
				delta = 0.45f;
			
			accum += delta;
			
			while(accum >= dt){
				screen.tick(dt, input);
				//input.tick();
				//t += dt;
				accum -= dt;
			}
			screen.render(delta);
			logo.render(delta);
			if(input.buttons[Haxput.ESCAPE])
				Gdx.app.exit();
		}
	}
		
	public void setScreen (Screen newScreen) {
		if (screen != null) screen.removed();
		screen = newScreen;
		if (screen != null) screen.init(this);
	}
	
	@Override
	public void pause () {
		running = false;
	}

	@Override
	public void resume () {
		running = true;
	}
	
	@Override
	public void resize (int width, int height) {
		Constants.SCREEN_MUL_X = (float)Haxmasher.FRAME_WIDTH/width;
		Constants.SCREEN_MUL_Y = (float)Haxmasher.FRAME_HEIGHT/height;
		screen.resize(width, height);
	}

	@Override
	public void dispose () {
	}
}
		
		//debugRenderer.render(sim.simulation, camera.combined.scale(Constants.PIXELS_PER_METER_X, Constants.PIXELS_PER_METER_Y, 1.0f));
