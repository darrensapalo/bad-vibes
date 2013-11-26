package com.mobi.badvibes.model.world;

import java.util.ArrayList;
import java.util.Random;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.train.Train;
import com.mobi.badvibes.nimators.WorldRendererAccessor;
import com.mobi.badvibes.view.WorldRenderer;

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
     * There are two stages of each train station. Entering is the state where
     * people are beginning to enter the train station. Arrival is when the
     * train comes to the station. Boarding is when the players are entering the
     * train. Departure is when the train leaves.
     * 
     * @author Darren
     * 
     */
    public enum WorldState
    {
        ENTERING, ARRIVAL, BOARDING, DEPARTURE
    }

    /**
     * This list will contain all the people in the train station, whether on
     * the train or not on the train.
     */
    protected ArrayList<Person> peopleList;

    /**
     * This array contains the destinations that persons will aim to go to.
     */
    protected ArrayList<Point>  targetPositions;

    protected Train             train;

    protected WorldState        currentState;

    public static final int     GRID_WIDTH  = 20;
    public static final int     GRID_HEIGHT = 9;

    public WorldRenderer        renderer;

    /**
     * This method begins creating the world by instantiating people. This
     * method determines the kinds of people to be created.
     */
    public abstract ArrayList<Person> createPeople();

    public void initialize()
    {
        currentState = WorldState.ENTERING;

        for (Person p : peopleList)
        {
            p.initialize(this);
        }
    }

    public World()
    {
        Instance = this;
        train = new Train();
        targetPositions = new ArrayList<Point>();
        setPeopleList(createPeople());
    }

    /**
     * Returns a random value for the grid.
     * 
     * @return The position in grid coordinates.
     */
    public Point getRandomCellCoordinate()
    {
        Random randomizer = new Random();

        ArrayList<Point> notAvailableCells = new ArrayList<Point>();

        // get the list of available spaces
        for (Person person : peopleList)
        {
            if (!person.getCellPoint().equals(new Point(-1, -1)))
            {
                notAvailableCells.add(person.getCellPoint());
            }
        }

        ArrayList<Point> availableCells = new ArrayList<Point>();

        for (int y = 2; y < GRID_HEIGHT - 1; y++)
        {
            for (int x = 1; x < GRID_WIDTH - 1; x++)
            {
                Point cellPoint = new Point(x, y);

                if (notAvailableCells.contains(cellPoint))
                {
                    continue;
                }

                availableCells.add(cellPoint);
            }
        }

        return availableCells.get(randomizer.nextInt(availableCells.size()));
    }

    public boolean checkIfPersonIsOccupying(Point point)
    {
        for (Person person : peopleList)
        {
            if (person.getCellPoint().equals(point))
            {
                return true;
            }
        }

        return false;
    }

    public void setInfoText(String info, int duration)
    {
        renderer.infoTextText = info;
        renderer.infoTextOpacity = 0;

        renderer.infoTextTextDirty = true;

        Timeline.createSequence()

        .push(Tween.to(renderer, WorldRendererAccessor.INFO_TEXT_OPACITY, 1).target(1))

        .push(Tween.to(renderer, WorldRendererAccessor.INFO_TEXT_OPACITY, 1).delay(duration).target(0))

        .start(BadVibes.tweenManager);
    }

    /**
     * /** /** This method returns the list of people from the current world.
     * 
     * @return
     */
    public ArrayList<Person> getPeopleList()
    {
        return peopleList;
    }

    public Train getTrain()
    {
        return train;
    }

    protected void setPeopleList(ArrayList<Person> peopleList)
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

    /**
     * This method does the logic for the world.
     * 
     * @param delta
     */
    public abstract void update(float delta);

    public WorldState getCurrentState()
    {
        return currentState;
    }

    public ArrayList<Point> getTargetPositions()
    {
        return targetPositions;
    }

    public void setTargetPositions(ArrayList<Point> targetPositions)
    {
        this.targetPositions = targetPositions;
    }
}
