package com.mobi.badvibes.model.people.logic;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;

public class StillLogic extends PersonLogic {

	public StillLogic(Person person){
		super(person);
		System.out.println("Oooh I'm happy now. yay.");
		Vector2 position = person.getView().getPosition().cpy();
		position.sub(0, GameDimension.PlatformOffset);
		World.getRandomPosition();
		
	}
	
	@Override
	public void think(float delta) {
		
	}
}
