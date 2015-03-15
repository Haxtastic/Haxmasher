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
package com.haxtastic.haxmasher.entity.masher;

import com.haxtastic.haxmasher.Constants;

public class Player extends Masher {
	public String nickname;
	public Player(int h, int xp, int dmg, int wpn, int arm, int lvl, String nickname, int crit) {
		super(h, xp, dmg, wpn, arm, "player", lvl, nickname, crit);
		x = Constants.Positions.playerX;
		y = Constants.Positions.playerY;
	}
	
	public void init() {
		
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
	}
}
