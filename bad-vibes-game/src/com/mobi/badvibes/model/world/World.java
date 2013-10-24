package com.mobi.badvibes.model.world;

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
	
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 10;
	public static final int GRID_CELL_SIZE = 32;
	
	private static float GRID_X_OFFSET = 5;
	private static float GRID_Y_OFFSET = 40;
	
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
		
		// Initialize placements
		peoplePlacements = new Person[GRID_WIDTH][GRID_HEIGHT];
		for (int y = 0; y < GRID_HEIGHT; y++)
			for (int x = 0; x < GRID_WIDTH; x++)
				peoplePlacements[y][x] = null;
		
		// Initialize people
		peopleList = new Array<Person>();
	}
	
	public static Vector2 getPosition(int gridx, int gridy){
		float x = gridx * GRID_WIDTH;
		float y = gridy * GRID_HEIGHT;
		
		x += GRID_X_OFFSET;
		y += GRID_Y_OFFSET;
		return new Vector2(x,y);
	}
}
