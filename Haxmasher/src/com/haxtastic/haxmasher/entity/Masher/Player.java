package com.haxtastic.haxmasher.entity.Masher;

import com.haxtastic.haxmasher.Constants;

public class Player extends Masher {
	public String nickname;
	public Player(String nick) {
		super(50, Constants.Elements.type.Fire, 0, 3, 2, "player", 1);
		nickname = nick;
	}
	
	public void init() {
	}
}
