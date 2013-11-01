package com.mobi.badvibes.controller.gameplay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;


public class DragGameplay extends GameplayStrategy {

	public Array<Person> personsReference;
	public Vector2 startPoint, endPoint, offset;
	public Person selectedPerson;
	public DragGameplay(World world) {
		super(world);
		
		personsReference = world.getPeopleList();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Point p = new Point(screenX, screenY);
		for(Person person : personsReference){
			PersonView view = person.getView();
			if (view.getBounds().contains(p.x, p.y)){
				selectedPerson = person;
				startPoint = view.getPosition();
				view.setCurrentState(State.PICKED_UP);
				float x = p.x - startPoint.x;
				float y = p.y - startPoint.y;
				offset = new Vector2(x, y);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (selectedPerson != null){
			selectedPerson.getView().setCurrentState(State.IDLE);
			selectedPerson = null;
			startPoint = null;
			endPoint = new Vector2(screenX, screenY);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (selectedPerson != null){
			PersonView view = selectedPerson.getView();
			view.setPosition(new Vector2(screenX - offset.x, screenY - offset.y));
			return true;
		}
		return false;
	}

}
