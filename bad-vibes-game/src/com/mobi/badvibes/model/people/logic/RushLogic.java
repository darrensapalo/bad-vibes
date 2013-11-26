package com.mobi.badvibes.model.people.logic;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
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
        
        Point   newPoint        = getFreePosition();
        Vector2 nextDestination = GameUtil.getPlatformVectorCentered(newPoint); 
        		
        person.getView().setDestination(nextDestination);
        person.setCellPoint(newPoint);
                
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
                        RushLogic.this.person.setLogic(new StillLogic(RushLogic.this.person));
                        RushLogic.this.person.walkingTween = null;
                    }
                }
            }).start(BadVibes.tweenManager);
    }

    private Point getFreePosition() {
    	for (Point p : person.getWorld().getTargetPositions())
    	{
    		if (World.Instance.checkIfPersonIsOccupying(p) == false){
    			person.setCellPoint(p);
    			return p;
    		}
    	}
		return World.Instance.getRandomCellCoordinate();
	}

	@Override
    public void think(float delta)
    {
        
    }
}
