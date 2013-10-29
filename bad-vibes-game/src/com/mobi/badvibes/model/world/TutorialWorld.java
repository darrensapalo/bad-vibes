package com.mobi.badvibes.model.world;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.NormanTheNormal;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.view.PersonView;

public class TutorialWorld extends World{

	public Array<Person> initialize() {
		Array<Person> list = new Array<Person>();
			NormanTheNormal sample = new NormanTheNormal();
			list.add(sample);
		return list;
	}

}
