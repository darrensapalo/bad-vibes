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
		// if (celly > 0)--celly;
		
		Vector2 realposition = person.getView().getPosition();
		Vector2 upperLeft = World.getPosition(cellx, celly);
		Vector2 upperRight = World.getPosition(cellx + 1, celly);
		Vector2 lowerLeft = World.getPosition(cellx, celly - 1);
		Vector2 lowerRight = World.getPosition(cellx + 1, celly - 1);
		
		// closest represents the best point the player should go to.
		Vector2 closest = upperLeft;
		float bestDistance;
		float tempDistance;
		bestDistance = getPreDistance(realposition, upperLeft);
		if ((tempDistance = getPreDistance(realposition, upperRight)) < bestDistance){
			closest = upperRight;
			bestDistance = tempDistance;
		}else if ((tempDistance = getPreDistance(realposition, lowerLeft)) < bestDistance){
			closest = lowerLeft;
			bestDistance = tempDistance;
		}else if ((tempDistance = getPreDistance(realposition, lowerRight)) < bestDistance){
			closest = lowerRight;
			bestDistance = tempDistance;
		}
		
		destination = closest;
		
		velocity = getDistance().nor();
		velocity.mul(0.35f);
		
		state = StillState.WalkingToMiniCell;
	}
	
	private float getPreDistance(Vector2 first, Vector2 second){
		return first.cpy().sub(second).len();
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
