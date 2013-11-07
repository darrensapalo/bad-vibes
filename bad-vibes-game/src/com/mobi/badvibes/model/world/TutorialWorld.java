package com.mobi.badvibes.model.world;

import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.NormanTheNormal;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.model.people.logic.RushLogic;

public class TutorialWorld extends World{

	public Array<Person> createPeople() {
		Array<Person> list = new Array<Person>();
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
		return list;
	}

	@Override
	public void runEvent(EventType type) {
		switch(type)
		{
			case RUSH:
				System.out.println("Rush!");
				for (Person p : peopleList){
					p.setLogic(new RushLogic(p));
				}
				break;
			case EXPLORE:
				System.out.println("Explore!");
				for (Person p : peopleList){
					p.setLogic(new ExploreLogic(p));
				}
		}
	}
	
	

}
