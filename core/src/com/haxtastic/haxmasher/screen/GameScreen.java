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

