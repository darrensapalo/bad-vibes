package com.mobi.badvibes.model.people.logic;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.controller.gameplay.DragGameplay.DragState;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.model.world.World.WorldState;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.view.GameDimension;

/**
 * This class will determine where the a person should go next.
 * 
 * @author micha_000
 * 
 */
public class RushLogic extends PersonLogic
{
	public RushLogic(Person person)
	{
		super(person);
		Point destination = null;
		WorldState worldState = World.Instance.getCurrentState();


		switch(worldState){
		case BOARDING:
			if (person.getCurrentCell().equals(World.Instance.destination)){
				person.setLogic(new HappyLogic(person));
				return;
			}else{
				destination = World.Instance.destination;
			}
			break;
		default:
			destination = (person.getDestinationCell() != null) ? person.getDestinationCell() : getFreePosition();
			break;
		}
		ComputeDestination(destination);
	}

	protected void ComputeDestination(Point destination){
		if (destination == null){
			person.setLogic(new StillLogic(person));
			return;
		}
		// If this is null, it means people are all around him
		// therefore stand still and wait.
		Vector2 nextDestination = GameUtil.getPlatformVectorCentered(destination); 

		person.getView().setDestination(nextDestination);
		person.setDestinationCell(destination);

		// compute the time it will take for the person to move from its current position to
		// the new position
		Vector2 curPosition = person.getView().getPosition();
		Vector2 newPosition = nextDestination;

		float distance  = curPosition.dst(newPosition);
		float time      = (distance / GameDimension.Cell.x) * Person.VELOCITY;

		if (person.walkingTween != null)
			person.walkingTween.free();

		// animate to that location
		person.walkingTween = Tween.to(person, PersonAccessor.POSITION, time).target(nextDestination.x, nextDestination.y)
				.setCallback(new TweenCallback()
				{
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1)
					{
						if (arg0 == TweenCallback.COMPLETE)
						{
							WorldState worldState = World.Instance.getCurrentState();
							if (worldState == WorldState.BOARDING){ /* logic, go to the next step */
								RushLogic.this.person.setLogic(new HappyLogic(RushLogic.this.person));
							}else{ /** Non boarding logic */
								RushLogic.this.person.setLogic(new StillLogic(RushLogic.this.person));
								RushLogic.this.person.walkingTween = null;
							}
						}
					}
				});
		
		
	}

	private Point getFreePosition() {
		return World.Instance.getRandomCellCoordinate();
	}

	@Override
	public void think(float delta)
	{
		if (person.state == DragState.Free && person.walkingTween.isStarted() == false){
			person.walkingTween.start(BadVibes.tweenManager);
		}
//		for (Person p : World.Instance.getPeopleList()){
//			if (p.equals(person)) continue;
//			if (p.getLogic() instanceof PauseLogic) continue;
//			PersonView view = p.getView();
//			if ((view.getHitBounds().overlaps(person.getView().getHitBounds())) && view.getCurrentBucketID() == person.getView().getCurrentBucketID())
//			{
//				person.displease();
//				person.setLogic(new PauseLogic(person, this, new Random().nextFloat() * 3f, Emotions.NAUGHTY));
//			}
//		}
	}
}
