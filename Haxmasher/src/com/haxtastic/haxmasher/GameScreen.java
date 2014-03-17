package com.haxtastic.haxmasher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.haxtastic.haxmasher.entity.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements HaxScreen {
	
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Scene scene;
	
	
	public GameScreen(Haxmasher haxmasher) {
		super();
		camera = new OrthographicCamera(Haxmasher.FRAME_WIDTH, Haxmasher.FRAME_HEIGHT);
		Constants.PIXELS_PER_METER_X = Haxmasher.FRAME_WIDTH/16;
		Constants.PIXELS_PER_METER_Y = Haxmasher.FRAME_HEIGHT/9;
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		batch = new SpriteBatch();
		Texture fontTexture = new Texture(Gdx.files.internal("fonts/hud_0.png"));
		fontTexture.setFilter(TextureFilter.Linear, TextureFilter.MipMapLinearLinear);
		TextureRegion fontRegion = new TextureRegion(fontTexture);
		font = new BitmapFont(Gdx.files.internal("fonts/hud.fnt"), fontRegion, false);
		font.setUseIntegerPositions(false);
		camera.update();
		scene = new Scene();
		
		
	}
	
	@Override
	public void tick(float dt) {
		Iterator<Actor> iter = scene.getActors().iterator();
		while(iter.hasNext()){
		    Actor actor = iter.next();
		    //System.out.println(actor.name);
		    actor.act(dt);
		}
	}
	
	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//batch.setColor(1, 1, 1, 1);
		Iterator<Actor> iter = scene.getActors().iterator();
		while(iter.hasNext()){
		    Actor actor = iter.next();
		    actor.draw(batch, font);
		}
		batch.end();
	}
	
	private void draw(Actor actor) {
		AtlasRegion spriteRegion =  Art.regions.get(actor.name);
		if(actor.width == 0)
			actor.width = spriteRegion.getRegionWidth();
		if(actor.height == 0)
			actor.height = spriteRegion.getRegionHeight();
		if(actor.width < 0) actor.width = -actor.width;
		float posX = actor.x * Constants.PIXELS_PER_METER_X;
		float posY = actor.y * Constants.PIXELS_PER_METER_X;
		batch.draw(spriteRegion, posX, posY, actor.width, actor.height);
	}
	
	private void draw(AtlasRegion spriteRegion, float x, float y, int width, int height) {
		if (width < 0) width = -width;
		float posX = x * Constants.PIXELS_PER_METER_X;
		float posY = y * Constants.PIXELS_PER_METER_X;
		batch.draw(spriteRegion, posX, posY, width, height);
	}
	
	private void draw(String image, float x, float y, int width, int height) {
		AtlasRegion spriteRegion =  Art.regions.get(image);
		draw(spriteRegion, x, y, width, height);
	}

	private void draw(String image, float x, float y) {
		AtlasRegion spriteRegion =  Art.regions.get(image);
		int width = spriteRegion.getRegionWidth();
		int height = spriteRegion.getRegionHeight();
		draw(spriteRegion, x, y, width, height);
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

