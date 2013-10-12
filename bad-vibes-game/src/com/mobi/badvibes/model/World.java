package com.mobi.badvibes.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.Person;

/**
 * Alternatively, you can call this the train station.
 * Each level of the game is considered a train station
 * or a World.
 * @author Darren
 *
 */
public abstract class World {
	
	/**
	 * There are two stages of each train station, the 
	 * pre-game wherein the player prepares for boarding
	 * or getting off, and the act of boarding and getting
	 * off itself.
	 * @author Darren
	 *
	 */
	enum State {
		PRE_GAME, GAME
	}
	
	/**
	 * This list will contain all the people in the train
	 * station, whether on the train or not on the train.
	 */
	protected Array<Person> peopleList;
	
	
	/**
	 * This list contains all the possible areas for people
	 * to stay in. Some Persons will try to come closer to 
	 * the passenger locations, while some will not respond
	 * to these locations. If there are two Persons contesting
	 * one location, tolerance and happiness respond accordingly.
	 */
	protected Array<Vector2> passengerLocations;
	
	/**
	 * This method begins creating the world, instantiating
	 * people, places in the train to stay in, etc.
	 */
	public abstract void initialize();
}
