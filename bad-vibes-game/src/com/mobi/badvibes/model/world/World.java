package com.mobi.badvibes.model.world;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.Person;
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
	 * This method begins creating the world by instantiating
	 * people. This method determines the kinds of people to be
	 * created.
	 */
	public abstract Array<Person> initialize();
	
	public World(){
		Instance = this;
		
		// Initialize people
		peopleList = initialize();
	}
	
	/**
	 * This method returns the center position of a random cell from the 
	 * mini grid of the platform. This means that each cell of the platform grid
	 * is subdivided into four parts. 
	 * @return the position of the mini cell
	 */
	public static Vector2 getRandomPosition(){
		Random r = new Random();
		int x = r.nextInt(GRID_WIDTH * 4);
		int y = r.nextInt(GRID_HEIGHT * 4);
		return getPosition(x, y);
	}
	
	/**
	 * This method returns the center position of a specified cell from the
	 * mini grid of the platform. This means that each cell of the platform grid
	 * is subdivided into four parts. 
	 * @param gridx the x axis
	 * @param gridy they axis
	 * @return the position of the mini cell
	 */
	public static Vector2 getPosition(int gridx, int gridy){
		if (gridx < 0)
			gridx = 0;
		else if (gridx > GRID_WIDTH * 4)
			gridx = GRID_WIDTH * 4;
		
		if (gridy < 0)
			gridy = 0;
		else if (gridy > GRID_HEIGHT * 4)
			gridy = GRID_HEIGHT * 4;
		
		float x = gridx * GameDimension.Cell.x / 4;
		float y = gridy * GameDimension.Cell.y / 4 + GameDimension.PlatformOffset;
		
		x += GameDimension.Cell.x / 8;
		y += GameDimension.Cell.y / 8;
		
		x += GameDimension.Person.x / 2;
		y += GameDimension.Person.y / 2;
		
		// y -= GameDimension.Person.y / 4;
		
		return new Vector2(x,y);
	}
	
	/**
	 * This method returns the center position of a specified cell from the
	 * grid of the platform.  
	 * @param gridx the x axis
	 * @param gridy the y axis
	 * @return the position of the cell
	 */
	protected static Vector2 getLargePosition(int gridx, int gridy){
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
	
	/**
	 * This method returns the center position of a specified cell from the
	 * grid of the platform.  
	 * @param gridx the x axis
	 * @param gridy the y axis
	 * @return the position of the cell
	 */
	public static Vector2 getRandomLargePosition(){
		Random r = new Random();
		int x = (int)(GameDimension.Person.x / 2) + r.nextInt(Gdx.graphics.getWidth() - (int)(GameDimension.Person.x / 2));
		int y = (int)((GameDimension.PlatformOffset)) + r.nextInt((int)(Gdx.graphics.getHeight() - GameDimension.PlatformOffset * 2));
		return new Vector2(x, y);
	}

	/**
	 * This method returns the list of people from the current world.
	 * @return
	 */
	public Array<Person> getPeopleList() {
		return peopleList;
	}

	/**
	 * This method runs a certain kind of event in the world. 
	 * @see EventType
	 * @param type
	 */
	public abstract void runEvent(EventType type);
}
