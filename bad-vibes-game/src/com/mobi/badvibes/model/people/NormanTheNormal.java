package com.mobi.badvibes.model.people;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.controller.gameplay.DragGameplay.DragState;
import com.mobi.badvibes.model.people.logic.LeavingTrainLogic;
import com.mobi.badvibes.model.people.logic.PauseLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.Emotions;

public class NormanTheNormal extends Person
{
    public NormanTheNormal()
    {
        super(PersonView.getView(PersonView.Character.NORMAN_THE_NORMAL));
    }

    public void update(float delta)
    {
//    	ArrayList<Person> peopleList = World.Instance.getPeopleList();
//    	ArrayList<Person> updated = new ArrayList<Person>();
//    	
//    	for (Person p : peopleList){
//    	    if (p.equals(this)) continue;
//            if (updated.contains(p) == true || updated.contains(this) == true)
//                continue;
//            if (p.getLogic() instanceof PauseLogic && getLogic() instanceof PauseLogic)
//                continue;
//            if (getLogic() instanceof LeavingTrainLogic)
//                continue;
//            if (p.state == DragState.Held || state == DragState.Held)
//                continue;
//
//            if (this.intersects(p))
//            {
//                System.out.println("Conflict: " + p + " and " + this);
//                updated.add(p);
//                updated.add(this);
//                Vector2 you = p.getView().getPosition().cpy();
//                Vector2 me = getView().getPosition().cpy();
//
//                Vector2 offset = you.sub(me);
//                setLogic(new PauseLogic(this, getLogic(), 2, Emotions.NAUGHTY, offset));
//                p.setLogic(new PauseLogic(this, p.getLogic(), 2, Emotions.ANGRY, offset.cpy().mul(-1)));
//            }
//    	}
    }

    @Override
    public void initialize(World theWorld, boolean inTrain)
    {
        super.initialize(theWorld, inTrain);
    }
}
