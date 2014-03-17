package com.haxtastic.haxmasher;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Art {
	
	public static HashMap<String, AtlasRegion> regions;
	public static TextureAtlas textureAtlas;
	
	public static void load () {
		regions = new HashMap<String, AtlasRegion>();
		textureAtlas = new TextureAtlas(Gdx.files.internal("textures/textures.atlas"));
		for (AtlasRegion r : textureAtlas.getRegions())
			regions.put(r.name, r);
	}
	
	public static TextureRegion load (String name, int width, int height) {
		Texture texture = new Texture(Gdx.files.internal(name));
		TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
		region.flip(false, true);
		return region;
	}
}
