package com.haxtastic.haxmasher.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.Haxmasher;
import com.haxtastic.haxmasher.Haxput;

public abstract class Screen {
	protected SpriteBatch batch;
	protected BitmapFont font;
	protected OrthographicCamera camera;
	protected Haxmasher mash;
	
	public final void init (Haxmasher haxmasher) {
		camera = new OrthographicCamera(Haxmasher.FRAME_WIDTH, Haxmasher.FRAME_HEIGHT);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		batch = new SpriteBatch();
		Texture fontTexture = new Texture(Gdx.files.internal("fonts/hud_0.png"));
		fontTexture.setFilter(TextureFilter.Linear, TextureFilter.MipMapLinearLinear);
		TextureRegion fontRegion = new TextureRegion(fontTexture);
		font = new BitmapFont(Gdx.files.internal("fonts/hud.fnt"), fontRegion, false);
		font.setUseIntegerPositions(false);
		font.setScale(1.2f, 1.35f);
		camera.update();
		mash = haxmasher;
	}
	
	public void tick(float dt, Haxput input) {
	}
	
	public abstract void render(float dt);
	public abstract void render(float dt, SpriteBatch b, BitmapFont f);
	
	public void removed () {
		//batch.dispose();
	}
	
	public void setScreen (Screen screen) {
		mash.setScreen(screen);
	}
	
	public void resize(int width, int height) {
		Constants.SCREEN_MUL_X = (float)Haxmasher.FRAME_WIDTH/width;
		Constants.SCREEN_MUL_Y = (float)Haxmasher.FRAME_HEIGHT/height;
	}

	public void show() {
	}

	public void hide() {
		//batch.end();
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
	}
}
