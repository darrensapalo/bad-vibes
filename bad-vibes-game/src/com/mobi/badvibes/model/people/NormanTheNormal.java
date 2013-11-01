package com.mobi.badvibes.model.people;

import java.util.Random;

import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;

public class NormanTheNormal extends Person{
	
	public NormanTheNormal() {
		// TODO add logic and view
		super(PersonView.getView(PersonView.Character.NORMAN_THE_NORMAL));

		Random r = new Random();
		view.setPosition(World.getPosition(r.nextInt(World.GRID_WIDTH), r.nextInt(World.GRID_HEIGHT)));
		logic = new ExploreLogic(this);
	}

	public void update(float delta) {
		
	}

}
