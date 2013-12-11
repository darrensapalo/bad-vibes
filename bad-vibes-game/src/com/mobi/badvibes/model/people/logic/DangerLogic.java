package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.controller.gameplay.DragGameplay.DragState;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.Emotions;
import com.mobi.badvibes.view.PersonView.Facing;
import com.mobi.badvibes.view.PersonView.State;

/**
 * This class will define a person that 
 * is worried about standing in the red
 * area of the train station.
 * 
 * By dropping a player here, the game
 * decreases points.
 * @author darren
 * 
 */
public class DangerLogic extends RushLogic
{
    public float    idleTime     = 0;
    public float    currTime     = 0;
    private boolean hasMovedAway = false;

    public DangerLogic(Person person)
    {
        super(person);

        PersonView view = person.getView();
        person.displease();
        
        // set a random time to be idle
        idleTime = 2 + new Random().nextFloat();
        currTime = idleTime;
        
        int x = person.getCurrentCell().x;
        Point p = new Point(x, 5);
        ComputeDestination(p);
    }

    @Override
    public void think(float delta)
    {
        if (person.getView().getCurrentState() == State.PICKED_UP)
        {
        	currTime = idleTime;
            return;
        }
        currTime += delta;

        if (currTime >= idleTime)
        {
            if (hasMovedAway == false){
                if (person.state == DragState.Free && person.walkingTween.isStarted() == false)
                {
                    hasMovedAway = true;
                    person.getView().setCurrentFacing(Facing.BACKWARD);
                    person.getView().setCurrentState(State.WALKING);
                    person.walkingTween.start(BadVibes.tweenManager);
                }
            }
            person.getView().setEmotion(person, Emotions.ANGRY);
            currTime = 0;
        }
    }
}
