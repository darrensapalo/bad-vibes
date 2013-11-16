package com.mobi.badvibes.model.people.logic;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView.State;

/**
 * This class will determine where the a person should go next.
 * 
 * @author micha_000
 * 
 */
public class ExploreLogic extends PersonLogic
{
    public ExploreLogic(Person person)
    {
        super(person);
        
        Point   newPoint        = World.getRandomCellCoordinate();
        Vector2 nextDestination = new Vector2(newPoint.x * GameDimension.Cell.x,
                                              newPoint.y * GameDimension.Cell.y + GameDimension.PlatformOffset);

        person.getView().setCurrentState(State.WALKING);
        
        person.setCellPoint(newPoint);
                
        // compute the time it will take for the person to move from its current position to
        // the new position
        Vector2 curPosition = person.getView().getPosition();
        Vector2 newPosition = nextDestination;
        
        float distance  = curPosition.dst(newPosition);
        float time      = (distance / GameDimension.Cell.x) * Person.VELOCITY;
        
        // animate to that location
        Tween.to(person, PersonAccessor.POSITION, time).target(nextDestination.x, nextDestination.y)
            .setCallback(new TweenCallback()
            {
                @Override
                public void onEvent(int arg0, BaseTween<?> arg1)
                {
                    if (arg0 == TweenCallback.COMPLETE)
                    {
                        ExploreLogic.this.person.setLogic(new StillLogic(ExploreLogic.this.person));
                    }
                }
            }).start(BadVibes.tweenManager);
    }

    @Override
    public void think(float delta)
    {
        
    }
}
