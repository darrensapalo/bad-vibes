package com.mobi.badvibes.model.people.logic;

import com.mobi.badvibes.model.people.Person;

/**
 * This abstract class defines one method called 
 * think which is implemented by subclasses. This
 * method dictates how the logic of the person works.
 * @author Darren
 *
 */
public abstract class PersonLogic {
	protected Person person;
	
	public PersonLogic(Person person){
		this.person = person;
	}
	
	/**
	 * This method handles 
	 * @param delta decimal seconds that has passed 
	 */
	public abstract void think(float delta);
}
