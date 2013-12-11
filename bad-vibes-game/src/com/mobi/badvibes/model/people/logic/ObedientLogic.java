package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.model.world.World.WorldState;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.Emotions;
import com.mobi.badvibes.view.PersonView.State;

/**
 * This class will define a person that is just standing still doing nothing.
 * 
 * @author micha_000
 * 
 */
public class ObedientLogic extends PersonLogic
{
    public float idleTime = 0;
    public float currTime = 0;

    public ObedientLogic(Person person)
    {
        super(person);

        PersonView view = person.getView();
        view.setCurrentState(State.IDLE);
        view.setEmotion(person, Emotions.HAPPY);
        
        if (person.isTaught == false)
            World.Instance.happiness += new Random().nextFloat() * 0.07f;
        
        person.isTaught = true;
        
        // Wait for 30 seconds
        idleTime = 4.5f;
        currTime = 0;
    }

    @Override
    public void think(float delta)
    {
        if (person.getView().getCurrentState() == State.PICKED_UP)
        {
            return;
        }
        
        if (World.Instance.getCurrentState() == WorldState.BOARDING){
        	currTime += delta;
            
            if (currTime >= idleTime)
            {
                // move to another spot
                person.setLogic(new RushLogic(person));
            }
        }

    }
}
