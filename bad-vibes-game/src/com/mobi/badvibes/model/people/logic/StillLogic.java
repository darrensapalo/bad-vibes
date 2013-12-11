package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.Emotions;
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

        PersonView view = person.getView();
        view.setCurrentState(State.IDLE);
        view.setEmotion(person, Emotions.HAPPY);
        
        if (person.hasArrivedAtPlatform == false)
            World.Instance.happiness += 0.03f + new Random().nextFloat() * 0.03f;
        person.hasArrivedAtPlatform = true;
        
        
        // set a random time to be idle
        idleTime = Person.MIN_IDLE_TIME + new Random().nextFloat() * Person.MAX_IDLE_TIME;
        currTime = Person.MIN_IDLE_TIME;
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
