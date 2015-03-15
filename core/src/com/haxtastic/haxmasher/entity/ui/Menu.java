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
package com.haxtastic.haxmasher.entity.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.Haxput;
import com.haxtastic.haxmasher.Constants.Buttons;
import com.haxtastic.haxmasher.entity.ui.Button.States;
import com.haxtastic.haxmasher.entity.ui.Button.States.state;

public class Menu {
	public List<Button> buttons = new ArrayList<Button>();
	
	public Menu() {
	}
	
	public List<Button> handleButtons(float dt, Haxput input) {
		Button button = null;
		ListIterator<Button> iterButtons = buttons.listIterator();
		while(iterButtons.hasNext()){
			button = iterButtons.next();
			if(!button.isState(Button.States.state.blocked)) {
				if(button.isWithin(input.touchDown[0], input.touchDown[1]) &&
					button.isWithin(input.touchUp[0], input.touchUp[1])) {
						button.pushed[Input.Buttons.LEFT] = input.mouseButtons[Input.Buttons.LEFT];
				} else if(button.isWithin(input.touchDown[0], input.touchDown[1])) {
					button.setState(Button.States.state.clicking);
				} else if(button.isWithin(input.touch[0], input.touch[1])) {
					button.setState(Button.States.state.hover);
				}
				float tX = (Gdx.input.getX()/Constants.PIXELS_PER_METER_X)*Constants.SCREEN_MUL_X;
				float tY = Gdx.input.getY() - Gdx.graphics.getHeight();
				if(tY < 0)
					tY = -tY;
				tY = (tY/Constants.PIXELS_PER_METER_Y)*Constants.SCREEN_MUL_Y;
				if(!Gdx.input.isTouched() && !button.isWithin(tX, tY))
					button.setState(Button.States.state.normal);
			}
		}
		if(input.touchUp[0] != 0f && input.touchUp[1] != 0f)
			input.resetTouch();
		//input.resetKeys();
		return buttons;
	}
	
	public void resetButtons(Haxput input) {
		Button button = null;
		ListIterator<Button> iterButtons = buttons.listIterator();
		while(iterButtons.hasNext()){
			button = iterButtons.next();
			button.reset();
		}
		//input.resetKeys();
		input.resetTouch();
	}
	
	public void clean() {
		buttons.clear();
	}
	
	public boolean removeButton(Button button) {
		return buttons.remove(button);
	}
	
	public Button addButton(Button button) {
		buttons.add(button);
		return button;
	}
	
	public Button addButton(String text, Buttons.type type, float x, float y, float width, float height) {
		return addButton(new Button(text, type, x, y, width, height));
	}

	public boolean hasState(state blocked) {
		Button button = null;
		ListIterator<Button> iterButtons = buttons.listIterator();
		while(iterButtons.hasNext()){
			button = iterButtons.next();
			if(button.getState() == States.state.blocked)
				return true;
		}
		return false;
	}
}
