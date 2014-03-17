package com.haxtastic.haxmasher;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Haxmasher implements ApplicationListener {
	public static final int FRAME_WIDTH = 1920;
	public static final int FRAME_HEIGHT = 1080;
	
	private final boolean started = false;
	private boolean running = false;
	private HaxScreen screen;
	
	private float accum = 0;
	private float dt = 1.0f / 45.0f;
	
	@Override
	public void create() {
		Art.load();
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		float delta = Gdx.graphics.getDeltaTime();
		
		if(delta > 0.45f)
			delta = 0.45f;
		
		accum += delta;
		
		while(accum >= dt){
			screen.tick(dt);
			//t += dt;
			accum -= dt;
		}
		screen.render(delta);
	}
		
		public void setScreen (HaxScreen newScreen) {
			if (screen != null) screen.hide();
			screen = newScreen;
			if (screen != null) screen.show();
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
		}

		@Override
		public void dispose () {
		}
}
		
		//debugRenderer.render(sim.simulation, camera.combined.scale(Constants.PIXELS_PER_METER_X, Constants.PIXELS_PER_METER_Y, 1.0f));
