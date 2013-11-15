package com.mobi.badvibes.model.world;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;

/**
 * Alternatively, you can call this the train station. Each level of the game is
 * considered a train station or a World.
 * 
 * @author Darren
 * 
 */
public abstract class World
{
    public static World Instance;

    /**
     * There are two stages of each train station, the pre-game wherein the
     * player prepares for boarding or getting off, and the act of boarding and
     * getting off itself.
     * 
     * @author Darren
     * 
     */
    enum State
    {
        PRE_GAME, GAME
    }

    /**
     * This list will contain all the people in the train station, whether on
     * the train or not on the train.
     */
    protected Array<Person> peopleList;

    public static final int GRID_WIDTH      = 20;
    public static final int GRID_HEIGHT     = 8;

    /**
     * This method begins creating the world by instantiating people. This
     * method determines the kinds of people to be created.
     */
    public abstract Array<Person> createPeople();

    public void initialize()
    {
        for (Person p : peopleList)
            p.initialize();
    }

    public World()
    {
        Instance = this;
        setPeopleList(createPeople());
    }

    /**
     * Returns a random value that ranges from 1 to 1 - GRID_WIDTH
     * @return The position in world coordinates.
     */
    public static Point getRandomCellCoordinate()
    {
        Random randomizer = new Random();
        
        int rx = 1 + randomizer.nextInt(GRID_WIDTH  - 2);
        int ry = 2 + randomizer.nextInt(GRID_HEIGHT - 2);
        
        System.out.println("X: " + rx + " Y: " + ry);
        
        // get random location
        return new Point(rx,
                         ry);
    }

    /**
     * This method returns the list of people from the current world.
     * 
     * @return
     */
    public Array<Person> getPeopleList()
    {
        return peopleList;
    }

    protected void setPeopleList(Array<Person> peopleList)
    {
        this.peopleList = peopleList;
    }

    /**
     * This method runs a certain kind of event in the world.
     * 
     * @see EventType
     * @param type
     */
    public abstract void runEvent(EventType type);
}
