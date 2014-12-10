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


public class EnemyStats {
	private static int	exp;
	private static int	level;
	
	public static int getExp() {
		return exp;
	}
	
	public static int getLevel() {
		return level;
	}
	
	public static int addExp(int e) {
		return setExp(exp+e);
	}

	public static int setExp(int e) {
		exp = e;
		return exp;
	}
	
	public static int setLevel(int lvl) {
		exp = lvl;
		return exp;
	}
}