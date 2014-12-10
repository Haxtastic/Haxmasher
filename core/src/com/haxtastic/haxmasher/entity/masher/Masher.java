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

import java.util.Random;

import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.Actor;

public abstract class  Masher extends Actor {
	public int	 					health;
	public int	 					curhealth;
	public int						maxhealth;
	public Constants.Elements.type element;
	public int	 					exp;
	public int	 					damage;
	public float					armor;
	public int	 					weapon;
	public int   					level;
	public String					nick;
	public int						crit;
	public float					tick;
	public boolean					lastCrit = false;
	
	public Masher(int h, int xp, int dmg, int wpn, float arm, String nme, int lvl, String nickname, int crt) {
		super(nme);
		height = Constants.Sizes.masherY;
		width = Constants.Sizes.masherX;
		maxhealth = health = curhealth = h;
		damage = dmg + wpn;
		weapon = wpn;
		armor = arm;
		nick = nickname;
		crit = crt;
		level = lvl;
		exp = xp;
	}
		
	public String attack(Masher target) {
		Random rand = new Random();
		String msg = "";
		String sCrit = "";
		float amount = rand.nextInt((int)(((getDamage())*(Constants.Stats.maxDamage - Constants.Stats.minDamage)))) + (damage*Constants.Stats.minDamage);
		amount-= amount*(target.armor/(Constants.Stats.armorReduction+target.armor));
		float critchance = rand.nextInt(100) + 1;
		if(this.getClass().equals(Guy.class))
			msg+=getNick() +  " attacks you";
		else if(this.getClass().equals(Player.class))
			msg+="You attack " + target.getNick();
		if(critchance <= crit) {
			amount*=2;
			sCrit =" critical";
		}
		int dmg = Math.round(amount);
		msg+= " for " + dmg + sCrit + " damage.";
		target.takeDamage(dmg);
		return msg;
	}
	
	@Override
	public void act(float dt) {
		if(curhealth < health)
			health-=maxhealth*tick;
		if(health < curhealth)
			health = curhealth;
	}
	
	public int takeDamage(int dmg) {
		curhealth-=dmg;
		if(curhealth <= 0) {
			curhealth = 0;
			tick = (0.05f);
		} else if((float)curhealth/(float)health < 0.5f)
			tick = (0.03f);
		else
			tick = (0.01f);
		return curhealth;
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
	
	public float addWeapon(int wpn) {
		return setWeapon(weapon+wpn);
	}
	
	public float setWeapon(int wpn) {
		weapon = wpn;
		return weapon;
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDamage() {
		return damage+weapon;
	}
	
	public int addDamage(int dmg) {
		return setDamage(damage+dmg);
	}
	
	public int setDamage(int dmg) {
		damage = dmg;
		return damage;
	}
	
	public int getExp() {
		return exp;
	}
	
	public int addExp(int xp) {
		return setExp(exp+xp);
	}
	
	public int setExp(int xp) {
		exp = xp;
		return exp;
	}
	
	public int expForLevel(int lvl){
		return Math.round((float)(50.0f/3.0f*(Math.pow(lvl, 3)-(6*Math.pow(lvl, 2))+(17*lvl)-12)));
	}
	
	public int checkLevel() {
		while(expForLevel(level+1) <= exp) {
			level++;
		}
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
	
	public int getHealth() {
		return health;
	}
	
	public int resetHealth() {
		return setHealth(maxhealth);
	}
	
	public int addHealth(int hp) {
		return setHealth(health+hp);
	}
	
	public int setHealth(int hp) {
		health = hp;
		return health;
	}
}
