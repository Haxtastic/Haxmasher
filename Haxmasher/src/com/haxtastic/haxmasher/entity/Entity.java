package com.haxtastic.haxmasher.entity;

public abstract class Entity {
	public float x;
	public float y;
	public float width, height;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
