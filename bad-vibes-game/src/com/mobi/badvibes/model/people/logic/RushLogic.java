package com.mobi.badvibes.model.people.logic;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.controller.gameplay.RushGameplay;
import com.mobi.badvibes.model.people.Person;

public class RushLogic extends PersonLogic{
	private Vector2 velocity;
	
	public RushLogic(Person person){
		super(person);
		Vector2 place = person.getView().getPosition().cpy();
		Vector2 distance = place.sub(RushGameplay.destination);
		velocity = distance.div(500f);
		System.out.println("Velocity is: " + velocity);
		System.out.println("Destination is: " + RushGameplay.destination);
		System.out.println();
		
	}
	
	public void think(float delta){
		Vector2 place = person.getView().getPosition().cpy();
		Vector2 distance = place.sub(RushGameplay.destination);
		if (distance.len() < velocity.len() * 10 ){
			person.getView().setPosition(RushGameplay.destination);
			person.setLogic(new ExploreLogic(person));
		}else
			person.getView().getPosition().sub(velocity);
	}
}
