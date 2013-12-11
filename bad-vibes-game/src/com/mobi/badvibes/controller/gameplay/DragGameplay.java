package com.mobi.badvibes.controller.gameplay;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ObedientLogic;
import com.mobi.badvibes.model.people.logic.RushLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;

public class DragGameplay extends Gameplay
{
	public enum DragState
	{
		Free, Held, FallingDown,
	}

	public ArrayList<Person>   personsReference;

	private static final float PICKUP_OFFSET = 15f;

	public DragGameplay(World world)
	{
		super(world);
		initialize();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		Vector2 pressPosition = new Vector2(screenX, screenY);

		for (final Person person : personsReference)
		{
			if (person.state != DragState.Free)
				continue;

			PersonView view = person.getView();
			
			if (view.getBounds().contains(pressPosition.x, pressPosition.y))
			{
				initTouch(person, pointer, pressPosition);
				initTouchDownTween(person);
				return true;
			}
		}

		return false;
	}

	/**
	 * This code creates a tween for the person to be picked up.
	 * This animation lasts for a short 50ms.
	 * @param person - the person to be picked up
	 */
	private void initTouchDownTween(final Person person) {
		Tween.to(person, PersonAccessor.PICKUP_OFFSET, 0.05f).target(0, -PICKUP_OFFSET).ease(Cubic.INOUT).setCallback(new TweenCallback()
		{
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1)
			{
				if (person != null)
					person.startPoint = person.getView().getPosition().cpy();
			}
		}).start(BadVibes.tweenManager);
	}

	/**
	 * This prepares the person to be picked up. 
	 * (1) recognizes the touch pointer used to pick up the person, 
	 * (2) removes any tweens the person currently has, 
	 * (3) removes any logic it is currently running, 
	 * (4) makes the art of the person look picked up,
	 * (5) sets the offset of the person. 
	 * @param person - the person to be initialized
	 * @param pointer - the touch pointer id 
	 * @param p - the point where the person was clicked
	 */
	private void initTouch(Person person, int pointer, Vector2 p) {
		PersonView view = person.getView();
		/* Set the pointer of the person */
		person.touchID = pointer;

		/* If the person has a tween, kill it and set it to null */
		if (person.walkingTween != null){
			person.walkingTween.kill();
			person.walkingTween = null;
		}

		/* Remove the person's logic */
		person.setLogic(null);
		
		/* You are now picked up */
		view.setCurrentState(State.PICKED_UP);
		person.state = DragState.Held;
		
		/* sound effects */
		MediaPlayer.sfx("drop");
		
		/* set the offset of the person */
		person.offset = person.getView().getPosition().cpy().sub(p);
		person.getView().setPickupCell(GameUtil.getPlatformPoint(p));

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		for (final Person person : personsReference)
		{
			if (person.touchID == pointer)
			{
				Point testPoint = ensureFallValidity(person, GameUtil.getPlatformPoint(new Vector2(screenX, screenY)));
				
				initFall(person, testPoint);
				initFallTween(person);
				return true;                
			}
		}

		return false;
	}

	private Point ensureFallValidity(Person person, Point testPoint) {
		if (World.Instance.checkIfPersonIsOccupying(person, testPoint))
		{
			ArrayList<Point> points = GameUtil.getSurroundingPoints(testPoint);
			for (Point p : points){
				if (World.Instance.checkIfPersonIsOccupying(person, p) == false){
					person.setDestinationCell(p);
					return p;
				}
			}
			testPoint = person.getView().getPickupCell();
		}
		person.setDestinationCell(testPoint);
		return testPoint;
		/* return the player to the previous place he was in */
	}

	/**
	 * This method prepares the falling tween for the person.
	 * After the tween is finished, 
	 * (1) the person's touch reference, state, and press start point is reset, 
	 * (2) the logic is replaced depending on the previous logic,
	 * (3) and the art of the person is set to idle
	 * @param person - the person to initialize falling
	 */
	private void initFallTween(final Person person) {
		Tween.to(person, PersonAccessor.PICKUP_OFFSET, 0.1f).target(0, 0).ease(Cubic.INOUT).setCallback(new TweenCallback()
		{
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1)
			{
				person.state = DragState.Free;

				if (person != null)
				{
					person.touchID = -1;

					if (person.getLogic() instanceof RushLogic == false){
						person.setLogic(new ObedientLogic(person));
					}else{
						person.setLogic(new RushLogic(person));
					}
					person.getView().setCurrentState(State.IDLE);
					person.state = DragState.Free;
					person.startPoint = null;
				}
			}
		}).start(BadVibes.tweenManager);


	}

	/**
	 * This prepares the person to fall. It 
	 * (1) finds out which cell you are planning to drop the person, 
	 * (2) sets the person's position there,
	 * (3) and makes the person's art look like he is falling. 
	 * @param person - the person to fall down
	 * @param screenX - the press event's x position
	 * @param screenY - the press event's y position
	 */
	private void initFall(Person person, Point testPoint) {
		/** Get the point of the cell's location */
		person.getView().setPosition(GameUtil.getPlatformVectorCentered(testPoint));
		person.state = DragState.FallingDown;
	}

	/**
	 * This finds out which person is being dragged, and updates
	 * that persons' cell position accordingly.
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		for (final Person person : personsReference)
		{
			if (person.touchID != pointer)
				continue;
			Point newPoint = GameUtil.getPlatformPoint(new Vector2(screenX, screenY));
			person.getView().setPosition(GameUtil.getPlatformVectorCentered(newPoint));                
		}
		return false;
	}

    @Override
    public void initialize()
    {
        personsReference = world.getPeopleList();
    }
}
