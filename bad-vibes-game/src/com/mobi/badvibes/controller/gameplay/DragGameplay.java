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
	public Point startPoint, endPoint, offset;
	public Person selectedPerson;
	public DragGameplay(World world) {
		super(world);
		
		personsReference = world.getPeopleList();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("down");
		Point p = new Point(screenX, screenY);
		for(Person person : personsReference){
			PersonView view = person.getView();
			if (view.getBounds().contains(p.x, p.y)){
				selectedPerson = person;
				startPoint = view.getPosition();
				view.setCurrentState(State.PICKED_UP);
				int x = p.x - startPoint.x;
				int y = p.y - startPoint.y;
				offset = new Point(x, y);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("up");
		if (selectedPerson != null){
			selectedPerson.getView().setCurrentState(State.IDLE);
			selectedPerson = null;
			startPoint = null;
			endPoint = new Point(screenX, screenY);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		System.out.println("drag");
		if (selectedPerson != null){
			PersonView view = selectedPerson.getView();
			view.setPosition(new Point(screenX - offset.x, screenY - offset.y));
			return true;
		}
		return false;
	}

}
