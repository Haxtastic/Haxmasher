package com.haxtastic.haxmasher.entity.Masher;

import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.Actor;

public abstract class  Masher extends Actor {
	public float 					health;
	public float					maxhealth;
	public Constants.Elements.type element;
	public float 					exp;
	public float 					damage;
	public float 					weapon;
	public int   					level;
	
	public Masher(int h, Constants.Elements.type e, float xp, float dmg, float wpn, String nme, int lvl) {
		super(nme);
		height = 2.0f;
		width= 2.0f;
		maxhealth = health = h;
		element = e;
		exp = xp;
		damage = dmg;
		weapon = wpn;
		level = lvl;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int addLevel(int lvl) {
		return setLevel(level+lvl);
	}
	
	public int setLevel(int lvl) {
		level = lvl;
		return level;
	}
	
	public float getWeapon() {
		return weapon;
	}
	
	public float addWeapon(float wpn) {
		return setWeapon(weapon+wpn);
	}
	
	public float setWeapon(float wpn) {
		weapon = wpn;
		return weapon;
	}
	
	public String getName() {
		return name;
	}
	
	public String setName(String nme) {
		name = nme;
		return name;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public float addDamage(float dmg) {
		return setDamage(damage+dmg);
	}
	
	public float setDamage(float dmg) {
		damage = dmg;
		return damage;
	}
	
	public float getExp() {
		return exp;
	}
	
	public float addExp(float xp) {
		return setExp(exp+xp);
	}
	
	public float setExp(float xp) {
		exp = xp;
		checkLevel();
		return exp;
	}
	
	public float expForLevel(int level){
		return (float) ((10*(Math.pow(level, 3)))-(25*(Math.pow(level, 2))));
	}
	
	public int checkLevel() {
		while(expForLevel(level) < exp)
			level++;
		return level;
	}
	
	public Constants.Elements.type getElement() {
		return element;
	}
	
	public Constants.Elements.type setElement(Constants.Elements.type ele) {
		for (Constants.Elements.type e : Constants.Elements.type.values()) {
			if (e == ele) {
				element = ele;
			    return element;
			}
		}
		return element;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float addHealth(float hp) {
		return setHealth(health+hp);
	}
	
	public float setHealth(float hp) {
		health = hp;
		return health;
	}
}
