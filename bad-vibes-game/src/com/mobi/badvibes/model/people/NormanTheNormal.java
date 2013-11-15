package com.mobi.badvibes.model.people;

import com.mobi.badvibes.view.PersonView;

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
        super.initialize();
    }
}
