package com.mobi.badvibes.model.people.logic;

import com.mobi.badvibes.model.people.Person;

public abstract class PersonLogic {
	protected Person person;
	
	public PersonLogic(Person person){
		this.person = person;
	}
	
	public abstract void think(float delta);
}
