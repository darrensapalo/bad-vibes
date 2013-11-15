package com.mobi.badvibes.model.people.logic;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.WorldRenderer;
import com.mobi.badvibes.view.PersonView.State;

/**
 * Explore logic determines where the person will go next. After determining the
 * location, this class will handle logic to <code>StillLogic</code>.
 * 
 * @author micha_000
 * 
 */
public class ExploreLogic extends PersonLogic
{
    public ExploreLogic(Person person)
    {
        super(person);
    }

    @Override
    public void think(float delta)
    {
        
    }
}
