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
import com.mobi.badvibes.util.MathHelper;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;
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
		personsReference = world.getPeopleList();

		Tween.registerAccessor(Person.class, new PersonAccessor());
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		Vector2 p = new Vector2(screenX, screenY);

		for (final Person person : personsReference)
		{
			if (person.state != DragState.Free)
			{
				continue;
			}

			PersonView view = person.getView();

			if (view.getBounds().contains(p.x, p.y))
			{
				person.touchID = pointer;

				if (person.walkingTween != null)
					person.walkingTween.kill();

				person.setLogic(null);
				person.getView().setCurrentState(State.PICKED_UP);

				person.state = DragState.Held;
				MediaPlayer.sfx("drop");

				Tween.to(person, PersonAccessor.PICKUP_OFFSET, 0.05f).target(0, -PICKUP_OFFSET).ease(Cubic.INOUT).setCallback(new TweenCallback()
				{
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1)
					{
						if (person != null)
							person.startPoint = person.getView().getPosition().cpy();
					}
				}).start(BadVibes.tweenManager);

				person.offset = view.getPosition().cpy().sub(p);
				view.setCurrentState(State.PICKED_UP);

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		for (final Person person : personsReference)
		{
			if (person.touchID == pointer)
			{
				/** Get the position of the touch minus the platform */
				int cellXPosition = MathHelper.Clamp((int) (screenX / GameDimension.Cell.x), 0, World.GRID_WIDTH - 1);
				int cellYPosition = MathHelper.Clamp((int) ((screenY - GameDimension.PlatformOffset) / GameDimension.Cell.y), 0, World.GRID_HEIGHT - 1);

				/** Get the point of the cell's location */
				Point newPoint = new Point(cellXPosition, cellYPosition);

				PersonView view = person.getView();
				view.setPosition(GameUtil.getPlatformVectorCentered(newPoint));

				person.state = DragState.FallingDown;
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
								person.getView().setCurrentState(State.IDLE);
								person.state = DragState.Free;
							}
							person.startPoint = null;
						}
					}
				}).start(BadVibes.tweenManager);

				return true;                
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		for (final Person person : personsReference)
		{
			if (person.touchID != pointer)
				continue;
			int cellXPosition = MathHelper.Clamp((int) (screenX / GameDimension.Cell.x), 0, World.GRID_WIDTH - 1);
			int cellYPosition = MathHelper.Clamp((int) ((screenY - GameDimension.PlatformOffset) / GameDimension.Cell.y), 0, World.GRID_HEIGHT - 1);

			PersonView view = person.getView();
			view.setPosition(GameUtil.getPlatformVectorCentered(new Point(cellXPosition, cellYPosition)));                
		}
		return false;
	}
}
