package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.view.PersonView.Emotions;
import com.mobi.badvibes.view.PersonView.State;

public class PauseLogic extends PersonLogic{
	private PersonLogic previousLogic;
	private float delay;
	private float counter;
	private State previousState;
    private Vector2 offset;
	public PauseLogic(Person person, PersonLogic nextLogic, float delay, Emotions emotion, Vector2 offset){
		super(person);
		this.previousLogic 	= nextLogic;
		this.delay 			= delay;
        this.offset = offset;
		this.counter 		= -(new Random().nextFloat() * 2f);
		this.previousState = person.getView().getCurrentState();
		person.getView().setCurrentState(State.IDLE);
		if (person.walkingTween != null){
			person.walkingTween.kill();
			person.walkingTween.free();
			person.walkingTween = null;
		}
		
		if (emotion != Emotions.NONE)
			person.getView().setEmotion(person, emotion);
		person.getView().getPosition().add(offset);
		
		MoveBack(offset);
	}

	protected void MoveBack(Vector2 offset){
        if (offset == null){
            person.setLogic(new StillLogic(person));
            return;
        }
        // compute the time it will take for the person to move from its current position to
        // the new position
        Vector2 curPosition = person.getView().getPosition();
        Vector2 newPosition = offset.cpy().mul(25);
        System.out.println(offset.len() + " offset is " + offset.x + ":" + offset.y);

        float distance  = curPosition.dst(newPosition);
        float time      = delay; // (distance / GameDimension.Cell.x) * Person.VELOCITY;

        if (person.walkingTween != null)
            person.walkingTween.free();

        // animate to that location
        person.walkingTween = Tween.to(person, PersonAccessor.POSITION, time).target(newPosition.x, newPosition.y)
                .setCallback(new TweenCallback()
                {
                    @Override
                    public void onEvent(int arg0, BaseTween<?> arg1)
                    {
                        if (arg0 == TweenCallback.COMPLETE)
                        {
                            person.setLogic(previousLogic);
                            person.getView().setCurrentState(previousState);
                        }
                    }
                });
        
        
    }

    @Override
    public void think(float delta)
    {
        // TODO Auto-generated method stub
        
    }

}
