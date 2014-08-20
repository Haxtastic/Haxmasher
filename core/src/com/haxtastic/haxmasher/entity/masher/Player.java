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
