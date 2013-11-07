package com.mobi.badvibes.model.people;

import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;

public class NormanTheNormal extends Person{
	
	public NormanTheNormal() {
		// TODO add logic and view
		super(PersonView.getView(PersonView.Character.NORMAN_THE_NORMAL));

	}

	public void update(float delta) {
		
	}
	
	@Override
	public void initialize() {
		view.setPosition(World.getRandomLargePosition());
		logic = new ExploreLogic(this);
	}

}
