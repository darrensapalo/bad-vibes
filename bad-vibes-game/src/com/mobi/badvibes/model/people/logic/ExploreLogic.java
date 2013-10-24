package com.mobi.badvibes.model.people.logic;

import com.mobi.badvibes.model.world.World;

public class ExploreLogic extends PersonLogic {
	public void think(float delta){
		World.getPosition(4, 4);
	}
}
