package com.mobi.badvibes.nimators;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.view.PersonView;

import aurelienribon.tweenengine.TweenAccessor;

public class PersonAccessor implements TweenAccessor<Person>
{
	public static final int OPACITY = 1;
	public static final int POSITION = 2;
	public static final int PICKUP_OFFSET = 3;
	public static final int EMOTION_OPACITY = 4;
    
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
        case PICKUP_OFFSET:
        	returnValues[0] = view.getPickupOffset().x;
        	returnValues[1] = view.getPickupOffset().y;
        	return 2;
        case EMOTION_OPACITY:
        	returnValues[0] = view.getEmotionOpacity();
        	return 1;
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
        case PICKUP_OFFSET:
        	Vector2 newPickupPosition = new Vector2(newValues[0], newValues[1]);
        	view.setPickupOffset(newPickupPosition);
        	break;
        case EMOTION_OPACITY:
        	target.getView().setEmotionOpacity(newValues[0]);
        	break;
        }
	}
}
