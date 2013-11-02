package com.mobi.badvibes.nimators;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.view.PersonView;

import aurelienribon.tweenengine.TweenAccessor;

public class PersonAccessor implements TweenAccessor<Person>
{
	public static final int OPACITY = 1;
	public static final int POSITION = 2;
    
	@Override
	public int getValues(Person target, int tweenType, float[] returnValues) {
		PersonView view = target.getView();
        switch (tweenType)
        {
        case OPACITY:
            returnValues[0] = view.getOpacity();
            return 1;
        case POSITION:
        	returnValues[0] = view.getPosition().x;
        	returnValues[1] = view.getPosition().y;
        	return 2;
        }

		return 0;
	}

	@Override
	public void setValues(Person target, int tweenType, float[] newValues) {
		PersonView view = target.getView();
        switch (tweenType)
        {
        case OPACITY:
            target.getView().setOpacity(newValues[0]);
            break;
        case POSITION:
        	Vector2 newPosition = new Vector2(newValues[0], newValues[1]);
        	view.setPosition(newPosition);
        	break;
        }
	}
}
