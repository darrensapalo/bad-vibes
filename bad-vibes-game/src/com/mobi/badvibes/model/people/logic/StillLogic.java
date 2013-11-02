package com.mobi.badvibes.model.people.logic;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView.State;

public class StillLogic extends PersonLogic {

	public enum StillState{
		WalkingToMiniCell,
		Still
	}
	private StillState state;
	private Vector2 destination;
	private Vector2 velocity;

	public StillLogic(Person person){
		super(person);
		
		Vector2 position = person.getView().getPosition().cpy();
		position.sub(0, GameDimension.PlatformOffset);
		int cellx = (int)position.x / (int)GameDimension.MiniCell.x;
		int celly = (int)position.y / (int)GameDimension.MiniCell.y;
		if (cellx > 0)
			--cellx;
		if (celly > 0)
			--celly;
		destination = World.getPosition(cellx, celly);
		
		System.out.print("My destination is " + new Point(cellx, celly));
		System.out.print(" which is (" + destination + ") ");
		
		velocity = getDistance().nor();
		velocity.mul(0.35f);
		System.out.println(" and I will walk at a rate of " + velocity.len());
		state = StillState.WalkingToMiniCell;
		
	}
	public Vector2 getDistance(){
		return destination.cpy().sub(person.getView().getPosition().cpy());
	}
	
	@Override
	public void think(float delta) {
		if (person.getView().getCurrentState() != State.PICKED_UP)
			switch(state){
			case WalkingToMiniCell:
				if (getDistance().len2() > velocity.len2())
					person.getView().setPosition(person.getView().getPosition().add(velocity));
				else
					person.getView().setPosition(destination);
				break;
			case Still:
				System.out.println("Hey I arrived now!");
				break;
			}
	}
}
