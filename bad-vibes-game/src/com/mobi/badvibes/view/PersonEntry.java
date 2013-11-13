package com.mobi.badvibes.view;

/**
 * This bean is used as a node in the cache for
 * the PersonView class. 
 * @author Michael Ong
 *
 */
public class PersonEntry {

	public PersonEntry(PersonView view) {
		this.view = view;
		this.taken = false;
	}

	public boolean taken;
	public PersonView view;
}
