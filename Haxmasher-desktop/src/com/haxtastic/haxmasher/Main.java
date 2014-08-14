package com.haxtastic.haxmasher;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Haxmasher";
		cfg.useGL20 = true;
		cfg.width = 960;
		cfg.height = 600;
		
		new LwjglApplication(new Haxmasher(), cfg);
	}
}
