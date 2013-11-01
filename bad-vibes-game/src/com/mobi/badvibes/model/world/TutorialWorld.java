package com.mobi.badvibes.model.world;

import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.NormanTheNormal;
import com.mobi.badvibes.model.people.Person;

public class TutorialWorld extends World{

	public Array<Person> initialize() {
		Array<Person> list = new Array<Person>();
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
			list.add(new NormanTheNormal());
		return list;
	}

}
