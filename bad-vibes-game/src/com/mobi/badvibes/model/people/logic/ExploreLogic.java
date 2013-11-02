package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;

public class ExploreLogic extends PersonLogic {
	
	private float counter;
	private PersonView view;
	private Vector2 destination;
	private Vector2 velocity;
	private float delayBeforeWalking;
	
	public ExploreLogic(Person person) {
		super(person);
		view = person.getView();
		delayBeforeWalking = 2f + new Random().nextFloat() * 2f;
	}

	public void think(float delta){
		if (view.getCurrentState() == State.IDLE)
			preWalking(delta);
		else if (view.getCurrentState() == State.WALKING)
			walking(delta);
	}
	
	private void walking(float delta){
		if (destination != null){
			Vector2 distance = destination.cpy().sub(view.getPosition());
			if (distance.len() < velocity.len()){
				view.setPosition(destination);
				view.setCurrentState(State.IDLE);
				destination = null;
			}
			view.setPosition(view.getPosition().add(velocity));
		}else{
			view.setCurrentState(State.IDLE);
		}
	}
	
	private void preWalking(float delta) {

		counter += delta;
		if (counter > delayBeforeWalking){
			counter = 0;
			view.setCurrentState(State.WALKING);
			destination = determineDestination(); 
		}
	}

	private Vector2 determineDestination() {
		Vector2 destination;
		Random r = new Random();
		delayBeforeWalking = 0.5f + r.nextFloat() * 6f;
		destination = World.getRandomPlatformPosition();
		Vector2 distance = destination.cpy().sub(view.getPosition());
		float rate = 250f;
		velocity = distance.div(rate);
		if (velocity.len() < 0.35f || velocity.len() > 1.35f){
			if (velocity.len() < 0.35f)
				velocity.nor().mul(0.35f);
			else if (velocity.len() > 1.35f)
				velocity.nor().mul(1.35f);
		}
		return destination;
	}
}
