package com.haxtastic.haxmasher.entity.masher;

import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.PlayerStats;

public class Guy extends Masher {
	public String nickname;
	public Guy(int h, int xp, int dmg, int wpn, int arm, int lvl, String nickname, int crit) {
		super(h, xp, dmg, wpn, arm, "enemy", lvl, nickname, crit);
		x = Constants.Positions.enemyX;
		y = Constants.Positions.enemyY;
	}
	
	public void init() {
		
	}
	
	@Override
	public void act(float dt) {
		super.act(dt);
	}
}
