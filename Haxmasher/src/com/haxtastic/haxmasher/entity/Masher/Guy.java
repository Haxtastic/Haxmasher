package com.haxtastic.haxmasher.entity.Masher;

import com.haxtastic.haxmasher.Constants;

public class Guy extends Masher {
	public String nickname;
	public Guy(String nick) {
		super(25, Constants.Elements.type.Fire, 0, 3, 0, "enemy", 1);
		nickname = nick;
	}
	
	public void init() {
		
	}
}
