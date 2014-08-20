package com.haxtastic.haxmasher.screen;

import com.haxtastic.haxmasher.Haxput;
import com.haxtastic.haxmasher.scene.Scene;
import com.haxtastic.haxmasher.scene.Stats;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends Screen {
	private Scene scene;
	
	
	public GameScreen() {
		setScene(new Stats());
	}
	
	@Override
	public void tick(float dt, Haxput input) {
		scene.act(dt, input);
	}
	
	@Override
	public void render(float dt) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		scene.draw(dt, batch, font);
		batch.end();
	}
	
	@Override
	public void render(float dt, SpriteBatch b, BitmapFont f) {
		scene.draw(dt, b, f);
	}
	
	public void setScene(Scene s) {
		if (scene != null) scene.removed();
		scene = s;
		if (scene != null) scene.init(this);
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		//batch.end();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}

