package com.mobi.badvibes.controller.gameplay;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;


public class DragGameplay extends GameplayStrategy{

	public enum DragState {
		Free,
		Held,
		FallingDown,
	}
	
	public Array<Person> personsReference;
	public Vector2 startPoint, endPoint, offset;
	public Person selectedPerson;
	private DragState state;
	private static final float PICKUP_OFFSET = 15f;
	private static DragGameplay Instance;
	
	public DragGameplay(World world) {
		super(world);
		Instance = this;
		personsReference = world.getPeopleList();
		Tween.registerAccessor(Person.class, new PersonAccessor());
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Point p = new Point(screenX, screenY);
		for(Person person : personsReference){
			PersonView view = person.getView();
			if (view.getBounds().contains(p.x, p.y)){
				selectedPerson = person;
				startPoint = view.getPosition();
				
				state = DragState.Held;
				
				Tween.to(person, PersonAccessor.POSITION, 0.2f)
				.targetRelative(0, -PICKUP_OFFSET)
				.ease(Cubic.INOUT)
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						if (selectedPerson != null)
							setStartOffset(selectedPerson.getView().getPosition());
					}
				})
				.start(BadVibes.tweenManager);
				
				view.setCurrentState(State.PICKED_UP);
				float x = p.x - startPoint.x;
				float y = p.y - startPoint.y;
				offset = new Vector2(x, y + PICKUP_OFFSET);
				return true;
			}
		}
		return true;
	}
	
	public boolean setStartOffset(Vector2 start){
		startPoint = start.cpy();
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (selectedPerson != null){
			state = DragState.FallingDown;
			endPoint = new Vector2(screenX, screenY);
			endTouch();
			return true;
		}
		return true;
	}

	public void endTouch(){
		selectedPerson.getView().setCurrentState(State.IDLE);
		Tween.to(selectedPerson, PersonAccessor.POSITION, 0.2f)
		.targetRelative(0, +PICKUP_OFFSET)
		.ease(Cubic.INOUT)
		.setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				state = DragState.Free;
				if (selectedPerson != null){
					selectedPerson.takeAPosition();
					selectedPerson = null;
					startPoint = null;
				}
			}
		})
		.start(BadVibes.tweenManager);
		
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		if (selectedPerson != null && startPoint != null){
			Vector2 finger = new Vector2(screenX, screenY);
			PersonView view = selectedPerson.getView();
			if (finger.sub(view.getPosition()).len() > 70){
				endTouch();
				return false;
			}
			view.setPosition(new Vector2(screenX - offset.x, screenY - offset.y));
			return true;
		}
		return true;
	}

}
