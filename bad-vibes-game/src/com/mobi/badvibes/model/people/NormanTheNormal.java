package com.mobi.badvibes.model.people;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.logic.StillLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;

public class NormanTheNormal extends Person
{
    public NormanTheNormal()
    {
        super(PersonView.getView(PersonView.Character.NORMAN_THE_NORMAL));
    }

    public void update(float delta)
    {

    }

    @Override
    public void initialize()
    {
        // we want to set the person's initial location before we do anything else
        
        Point   newLocation     = World.getRandomCellCoordinate();
        Vector2 personLocation  = new Vector2(newLocation.x * GameDimension.Cell.x,
                                              newLocation.y * GameDimension.Cell.y);
        
        getView().setPosition(personLocation.add(0, GameDimension.PlatformOffset));
        getView().setCurrentState(State.WALKING);
        
        setCellPoint(newLocation);
        setLogic(new StillLogic(this));
    }
}
