package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.view.PersonView.Emotions;
import com.mobi.badvibes.view.PersonView.State;

public class PauseLogic extends PersonLogic{
	private PersonLogic previousLogic;
	private float delay;
	private float counter;
	private State previousState;
	public PauseLogic(Person person, PersonLogic nextLogic, float delay, Emotions emotion){
		super(person);
		this.previousLogic 	= nextLogic;
		this.delay 			= delay;
		this.counter 		= -(new Random().nextFloat() * 2f);
		this.previousState = person.getView().getCurrentState();
		person.getView().setCurrentState(State.IDLE);
		if (person.walkingTween != null)
			person.walkingTween.pause();
		if (emotion != Emotions.NONE)
			person.getView().setEmotion(person, emotion);
	}

	public void think(float delta) {
		counter += delta;
		if (counter >= delay){

			if (person.walkingTween != null)
				person.walkingTween.resume();
			person.setLogic(previousLogic);
			person.getView().setCurrentState(previousState);
		}
	}

}
