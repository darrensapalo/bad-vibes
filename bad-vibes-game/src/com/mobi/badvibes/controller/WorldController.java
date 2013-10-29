package com.mobi.badvibes.controller;

import com.mobi.badvibes.model.world.TutorialWorld;
import com.mobi.badvibes.model.world.World;

public class WorldController {

	protected World world;
	
	public WorldController(){
		world = new TutorialWorld();
	}
	
	public void update(float delta) {
		
	}

	public World getWorld() {
		return world;
	}

}
