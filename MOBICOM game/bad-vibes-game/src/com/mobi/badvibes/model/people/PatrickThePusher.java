package com.mobi.badvibes.model.people;

import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;

public class PatrickThePusher extends Person
{
    public PatrickThePusher()
    {
        super(PersonView.getView(PersonView.Character.PATRICK_THE_PUSHER));
    }

    public void update(float delta)
    {

    }

    @Override
    public void initialize(World theWorld, boolean inTrain)
    {
        super.initialize(theWorld, inTrain);
    }
}
