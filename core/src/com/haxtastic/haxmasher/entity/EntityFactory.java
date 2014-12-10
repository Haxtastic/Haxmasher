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
package com.haxtastic.haxmasher.entity;

import java.util.Random;

import com.haxtastic.haxmasher.Constants;
import com.haxtastic.haxmasher.entity.masher.Guy;
import com.haxtastic.haxmasher.entity.masher.Player;

public class EntityFactory {
	
	public static Player createPlayer() {
		int health = Math.round(Constants.Stats.healthWeight*PlayerStats.pHealth + PlayerStats.level*Constants.Stats.healthWeight);
		int damage = Math.round(Constants.Stats.damageWeight*PlayerStats.pDamage + PlayerStats.level*Constants.Stats.damageWeight);
		int armor = Math.round(Constants.Stats.armorWeight*PlayerStats.pArmor + PlayerStats.level*Constants.Stats.armorWeight);
		int crit = Math.round(Constants.Stats.critWeight*PlayerStats.pCrit + PlayerStats.level*Constants.Stats.critWeight);
		return new Player(health, PlayerStats.exp, damage, 0, armor, PlayerStats.level, "Hero", crit);
	}
	
	public static Guy createGuy(int level) {
		Random rand = new Random();
		level = Long.valueOf(Math.round(Math.random() * (((level+1) - (level-1)) + 1) + (level-1))).intValue();
		if(level <= 0)
			level = 1;
		int points = level*Constants.Stats.levelWeight;
		int point[] = genNumbers(4, points);
		int health = Constants.Stats.healthWeight*point[0] + Constants.Stats.healthWeight;
		int damage = Constants.Stats.damageWeight*point[1] + Constants.Stats.damageWeight;
		int armor = Constants.Stats.armorWeight*point[2] + Constants.Stats.armorWeight;
		int crit = Constants.Stats.critWeight*point[3] + Constants.Stats.critWeight;
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rand.nextInt(5)+3; i++) {
		    char c = chars[rand.nextInt(chars.length)];
		    sb.append(c);
		}
		String nick = sb.toString();
		return new Guy(health, ((point[rand.nextInt(3)]+10)*25)*level, damage, 0, armor, level, nick, crit);
	}
	
    public static int[] genNumbers(int n, int sum){
        int[] nums = new int[n];
        int upperbound = Long.valueOf(Math.round(sum*1.0/n)).intValue();
        int offset = Long.valueOf(Math.round(0.5*upperbound)).intValue();
       
        int cursum = 0;
        Random random = new Random(new Random().nextInt());
        for(int i=0 ; i < n ; i++){
            int rand = random.nextInt(upperbound) + offset;
            if( cursum + rand > sum || i == n - 1) {
                rand = sum - cursum;
            }
            cursum += rand;
            nums[i]=rand;
            if(cursum == sum){
                break;
            }
        }
        for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 0) {
				nums[i]++;
				nums[i == 0 ? i+1 : i-1]-=1;
			}
		}
        return nums;
    }
	
}
