package com.mobi.badvibes.controller.gameplay;

import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;


public class DragGameplay extends GameplayStrategy {

	public Array<Person> personsReference;
	public DragGameplay(World world) {
		super(world);
		
		personsReference = world.getPeopleList();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Point p = new Point(screenX, screenY);
		for(Person person : personsReference){
			return false;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

}
