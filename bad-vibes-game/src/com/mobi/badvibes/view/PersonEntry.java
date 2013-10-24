package com.mobi.badvibes.view;

public class PersonEntry {

	public PersonEntry(PersonView view) {
		this.view = view;
		this.taken = false;
	}

	public boolean taken;
	public PersonView view;
}
