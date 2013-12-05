package com.mobi.badvibes.model.world;

import java.util.ArrayList;
import java.util.Random;

import com.mobi.badvibes.Point;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.model.people.NormanTheNormal;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ExploreLogic;
import com.mobi.badvibes.model.people.logic.LeavingTrainLogic;
import com.mobi.badvibes.model.people.logic.ObedientLogic;
import com.mobi.badvibes.model.people.logic.RushLogic;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.view.TrainView.TrainState;

public class TutorialWorld extends World
{

    /**
     * This dictates the number of seconds before the train arrives.
     */
    private static final float ArrivalTime   = 12;

    /**
     * This dictates how much time is allocated for boarding, including the opening and closing of doors.
     */
    private static final float BoardingTime  = 12;
    /**
     * The delay before the next train arrives.
     */
    private static final float NextTrainTime = 10;

    /**
     * It takes 5 seconds to go back to the main menu screen. 
     */
	private static final float BackToMenuDelay = 5;

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
            	if (p.getLogic() instanceof ObedientLogic == false)
            		p.setLogic(new RushLogic(p));
            }
		case ALIGHT:
			System.out.println("Alighting the train!");
			for (Person p : peopleInTrainList){
				Random r = new Random();
				Point newPoint = (r.nextBoolean()) ? new Point(9, 0): new Point(10, 0);
				p.getView().setPosition(GameUtil.getPlatformVectorCentered(newPoint));
				p.setLogic(new LeavingTrainLogic(p));
			}
            break;
        case EXPLORE:
            System.out.println("Explore!");
            for (Person p : peopleList)
            {
                p.setLogic(new ExploreLogic(p));
            }
		default:
			break;
        }
    }

    @Override
    public void update(float delta)
    {
        Timer += delta;
        if (peopleInTrainList.size() == 0){
        	if (peopleList.size() == 0){
        		setInfoText("Level success!", 2);
        		currentState = WorldState.END_GAME;
        	}
        }
        switch (currentState)
        {
        case ENTERING:
            if (Timer >= ArrivalTime)
            {
                Timer = 0;
                train.trainView.arriveTrain();
                currentState = WorldState.ARRIVAL;

                setInfoText("The train is arriving!", 3);
                setPeopleInTrainList(createPeopleInTrain());
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
        case END_GAME:
        	if (Timer >= BackToMenuDelay){
        		GameMaster.endGame();
        	}
        	break;

        default:
            break;
        }
    }
}
