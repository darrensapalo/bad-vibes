package com.mobi.badvibes.view;

/**
 * This class is used as a node in the cache for the PersonView class.
 * 
 * @author Darren
 * 
 */
public class PersonEntry
{

    public PersonEntry(PersonView view)
    {
        this.view = view;
        this.taken = false;
    }

    public boolean    taken;
    public PersonView view;
}
