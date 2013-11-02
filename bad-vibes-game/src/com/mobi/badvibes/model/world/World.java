package com.mobi.badvibes.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.RushLogic;
import com.mobi.badvibes.view.GameDimension;

/**
 * Alternatively, you can call this the train station.
 * Each level of the game is considered a train station
 * or a World.
 * @author Darren
 *
 */
public abstract class World {
	public static World Instance;
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
	
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 5;


	
	/**
	 * This grid contains the platform grid that have the 
	 * players.
	 */
	protected Person[][] peoplePlacements;
	
	/**
	 * This method begins creating the world by instantiating
	 * people. This method determines the kinds of people to be
	 * created.
	 */
	public abstract Array<Person> initialize();
	
	public World(){
		Instance = this;
		
		// Initialize placements
		peoplePlacements = new Person[GRID_HEIGHT][GRID_WIDTH];
		for (int y = 0; y < GRID_HEIGHT; y++)
			for (int x = 0; x < GRID_WIDTH; x++)
				peoplePlacements[y][x] = null;
		
		// Initialize people
		peopleList = initialize();
	}
	
	public static Vector2 getPosition(int gridx, int gridy){
		return getVectorPosition(gridx, gridy);
	}
	
	protected static Vector2 getVectorPosition(int gridx, int gridy){
		if (gridx < 0 || gridx > GRID_WIDTH)
			gridx = 0;
		if (gridy < 0 || gridy > GRID_HEIGHT)
			gridy = 0;
		
		float x = gridx * GameDimension.Cell.x;
		float y = gridy * GameDimension.Cell.y + GameDimension.PlatformOffset;
		
		x += GameDimension.Cell.x / 2;
		y += GameDimension.Cell.y / 2;
		
		x += GameDimension.Person.x / 2;
		y += GameDimension.Person.y / 2;
		
		y -= GameDimension.Person.y / 4;
		
		return new Vector2(x,y);
	}

	public Array<Person> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(Array<Person> peopleList) {
		this.peopleList = peopleList;
	}
	
	public abstract void runEvent(EventType type);
}
