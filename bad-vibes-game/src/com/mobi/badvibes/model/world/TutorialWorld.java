package com.mobi.badvibes.model.world;

import java.util.ArrayList;

import com.mobi.badvibes.model.people.NormanTheNormal;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.model.people.logic.RushLogic;
import com.mobi.badvibes.view.TrainView.TrainState;

public class TutorialWorld extends World
{

    /**
     * People have 4 seconds to enter the train station.
     */
    private static final float ArrivalTime   = 12;

    /**
     * The train allows people to board for 7 seconds.
     */
    private static final float BoardingTime  = 7;
    /**
     * The next train arrives in 10 seconds.
     */
    private static final float NextTrainTime = 10;

    private float              Timer         = 0;

    public ArrayList<Person> createPeople()
    {
        ArrayList<Person> list = new ArrayList<Person>();

        for (int i = 0; i < 10; i++)
            list.add(new NormanTheNormal());

        return list;
    }

    public ArrayList<Person> createPeopleInTrain()
    {
        ArrayList<Person> list = new ArrayList<Person>();
        
        for (int i = 0; i < 5; i++)
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

    @Override
    public void update(float delta)
    {
        Timer += delta;

        switch (currentState)
        {
        case ENTERING:
            if (Timer >= ArrivalTime)
            {
                Timer = 0;
                train.trainView.arriveTrain();
                currentState = WorldState.ARRIVAL;
                setInfoText("The train is arriving!", 5);
            }
            break;
        case ARRIVAL:
            if (train.trainView.currentState == TrainState.BOARDING)
            {
                Timer = 0;
                currentState = WorldState.BOARDING;
                runEvent(EventType.RUSH);
            }
            break;
        case BOARDING:
            if (Timer >= BoardingTime)
            {
                Timer = 0;
                currentState = WorldState.DEPARTURE;
                train.trainView.departTrain();
            }
            break;
        case DEPARTURE:
            if (Timer >= NextTrainTime)
            {
                Timer = 0;
                currentState = WorldState.ENTERING;
            }
            break;

        default:
            break;
        }
    }
}
