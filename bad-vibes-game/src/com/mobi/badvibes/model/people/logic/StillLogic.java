package com.mobi.badvibes.model.people.logic;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView.State;

/**
 * This class will handle the "walking part" of the logic of the person. It
 * reads the newly defined cell location assigned by <code>ExploreLogic</code>
 * and from there it will get the new location that the person will go to. It
 * then makes a tween from its current position to the position it will go to
 * next. After that, it will wait for a specific amount of time before exploring
 * again.
 * 
 * @author micha_000
 * 
 */
public class StillLogic extends PersonLogic
{
    public StillLogic(Person person)
    {
        super(person);
    }

    @Override
    public void think(float delta)
    {

    }
}
