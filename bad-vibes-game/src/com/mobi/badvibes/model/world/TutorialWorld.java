package com.mobi.badvibes.model.world;

import java.util.ArrayList;

import com.mobi.badvibes.model.people.NormanTheNormal;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.model.people.logic.RushLogic;

public class TutorialWorld extends World
{
    public ArrayList<Person> createPeople()
    {
        ArrayList<Person> list = new ArrayList<Person>();

        for (int i = 0; i < 10; i++)
            list.add(new NormanTheNormal());

        return list;
    }

    @Override
    public void runEvent(EventType type)
    {
        switch (type)
        {
        case RUSH:
            System.out.println("Rush!");
            for (Person p : peopleList)
            {
                p.setLogic(new RushLogic(p));
            }
            break;
        case EXPLORE:
            System.out.println("Explore!");
            for (Person p : peopleList)
            {
                p.setLogic(new ExploreLogic(p));
            }
        }
    }
}
