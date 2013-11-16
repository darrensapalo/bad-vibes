package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.view.PersonView.State;

/**
 * This class will define a person that is just standing still doing nothing.
 * 
 * @author micha_000
 * 
 */
public class StillLogic extends PersonLogic
{
    public float idleTime = 0;
    public float currTime = 0;

    public StillLogic(Person person)
    {
        super(person);

        person.getView().setCurrentState(State.IDLE);
        
        // set a random time to be idle
        idleTime = Person.MIN_IDLE_TIME + new Random().nextFloat() * Person.MAX_IDLE_TIME;
        currTime = 0;
    }

    @Override
    public void think(float delta)
    {
        if (person.getView().getCurrentState() == State.PICKED_UP)
        {
            return;
        }
        
        currTime += delta;

        if (currTime >= idleTime)
        {
            // move to another spot
            person.setLogic(new ExploreLogic(person));
        }
    }
}
