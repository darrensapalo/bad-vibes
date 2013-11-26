package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;

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
public class LeavingTrainLogic extends PersonLogic
{
    public LeavingTrainLogic(Person person)
    {
        super(person);
        Random r = new Random();
        Point   newPoint        = new Point(r.nextInt(World.GRID_WIDTH), 9);
        Vector2 nextDestination = GameUtil.getPlatformVectorCentered(newPoint); 
        		
        person.getView().setDestination(nextDestination);
        person.setCellPoint(newPoint);
                
        // compute the time it will take for the person to move from its current position to
        // the new position
        Vector2 curPosition = person.getView().getPosition();
        Vector2 newPosition = nextDestination;
        
        float distance  = curPosition.dst(newPosition);
        Random random = new Random();
		float time      = (distance / GameDimension.Cell.x) * Person.VELOCITY * (1 + person.getWeight() * random.nextFloat());
        
        // animate to that location
        person.walkingTween = Tween.to(person, PersonAccessor.POSITION, time)
        	.ease(TweenEquations.easeNone)
    		.target(nextDestination.x, nextDestination.y)
            .setCallback(new TweenCallback()
            {
                @Override
                public void onEvent(int arg0, BaseTween<?> arg1)
                {
                    if (arg0 == TweenCallback.COMPLETE)
                    {
                    	World.Instance.getPeopleInTrainList().remove(this);
                        LeavingTrainLogic.this.person.setLogic(new HappyLogic(LeavingTrainLogic.this.person));
                        LeavingTrainLogic.this.person.walkingTween = null;
                    }
                }
            }).start(BadVibes.tweenManager);
    }

    @Override
    public void think(float delta)
    {
        
    }
}
