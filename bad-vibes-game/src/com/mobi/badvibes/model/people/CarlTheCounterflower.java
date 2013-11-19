package com.mobi.badvibes.model.people;

import com.mobi.badvibes.view.PersonView;

public class CarlTheCounterflower extends Person
{
    public CarlTheCounterflower()
    {
        super(PersonView.getView(PersonView.Character.CARL_THE_COUNTERFLOWER));
    }

    public void update(float delta)
    {

    }

    @Override
    public void initialize()
    {
        super.initialize();
    }
}
