package com.haxtastic.haxmasher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Haxput implements InputProcessor {
	public float touchDown[] = new float[2];
	public float touchUp[] = new float[2];
	public float touch[] = new float[2];
	public static final int ESCAPE = 0;
	public static final int CTRL = 1;
	public static final int SHIFT = 2;
	
	
	public boolean[] tempButtons = new boolean[252];
	
	public boolean[] mouseButtons = new boolean[3];
	public boolean[] mouseOldButtons = new boolean[3];
	public boolean[] buttons = new boolean[3];
	public boolean[] oldButtons = new boolean[3];
	
	public Haxput() {
	}
	
	public void resetTouch() {
		touchDown[0] = touchDown[1] = touchUp[0] = touchUp[1] = 0f;
		for (int i = 0; i < mouseButtons.length; i++) {
			mouseButtons[i] = false;
		}
	}
	
	public void resetKeys() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = false;
		}
	}
	
	public void set(int key, boolean down) {
		int button = -1;

		if(key == Keys.ESCAPE || key == Keys.MENU) button = ESCAPE;
		if(key == Keys.SHIFT_LEFT || key == Keys.SHIFT_RIGHT) button = SHIFT;
		if(key == Keys.CONTROL_LEFT || key == Keys.CONTROL_RIGHT) button = CTRL;

		if (button >= 0) {
			buttons[button] = down;
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		set(keycode, true);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		set(keycode, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY-=Gdx.graphics.getHeight();
		if(screenY < 0)
			screenY = -screenY;
		mouseButtons[button] = true;
		touchDown[0] = (screenX/Constants.PIXELS_PER_METER_X)*Constants.SCREEN_MUL_X;
		touchDown[1] = (screenY/Constants.PIXELS_PER_METER_Y)*Constants.SCREEN_MUL_Y;
		//touchUp[0] = touchUp[1] = 0f;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY-=Gdx.graphics.getHeight();
		if(screenY < 0)
			screenY = -screenY;
		//mouseButtons[button] = false;
		touchUp[0] = (screenX/Constants.PIXELS_PER_METER_X)*Constants.SCREEN_MUL_X;
		touchUp[1] = (screenY/Constants.PIXELS_PER_METER_Y)*Constants.SCREEN_MUL_Y;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenY-=Gdx.graphics.getHeight();
		if(screenY < 0)
			screenY = -screenY;
		touch[0] = (screenX/Constants.PIXELS_PER_METER_X)*Constants.SCREEN_MUL_X;
		touch[1] = (screenY/Constants.PIXELS_PER_METER_Y)*Constants.SCREEN_MUL_Y;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		screenY-=Gdx.graphics.getHeight();
		if(screenY < 0)
			screenY = -screenY;
		touch[0] = (screenX/Constants.PIXELS_PER_METER_X)*Constants.SCREEN_MUL_X;
		touch[1] = (screenY/Constants.PIXELS_PER_METER_Y)*Constants.SCREEN_MUL_Y;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}