package com.mobi.badvibes.controller.gameplay;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.MathHelper;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;

public class DragGameplay extends GameplayStrategy
{
    public enum DragState
    {
        Free, Held, FallingDown,
    }

    public Array<Person>       personsReference;
    public Vector2             startPoint, offset;
    public Person              selectedPerson;
    private DragState          state;
    private static final float PICKUP_OFFSET = 15f;

    public DragGameplay(World world)
    {
        super(world);
        personsReference = world.getPeopleList();
        Tween.registerAccessor(Person.class, new PersonAccessor());
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector2 p = new Vector2(screenX, screenY);
        for (Person person : personsReference)
        {
            PersonView view = person.getView();
            
            if (view.getBounds().contains(p.x, p.y))
            {
                selectedPerson  = person;
                state           = DragState.Held;

                Tween.to(person, PersonAccessor.PICKUP_OFFSET, 0.2f).target(0, -PICKUP_OFFSET).ease(Cubic.INOUT).setCallback(new TweenCallback()
                {
                    @Override
                    public void onEvent(int arg0, BaseTween<?> arg1)
                    {
                        if (selectedPerson != null)
                            startPoint = selectedPerson.getView().getPosition().cpy();
                    }
                }).start(BadVibes.tweenManager);
                
                offset = view.getPosition().cpy().sub(p);
                view.setCurrentState(State.PICKED_UP);
                
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (selectedPerson != null)
        {
            state = DragState.FallingDown;
            endTouch();
            return true;
        }
        return true;
    }

    public void endTouch()
    {
        selectedPerson.getView().setCurrentState(State.IDLE);
        Tween.to(selectedPerson, PersonAccessor.PICKUP_OFFSET, 0.2f).target(0, 0).ease(Cubic.INOUT).setCallback(new TweenCallback()
        {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1)
            {
                state = DragState.Free;
                if (selectedPerson != null)
                {
                    selectedPerson.takeAPosition();
                    
                    selectedPerson  = null;
                    startPoint      = null;
                }
            }
        }).start(BadVibes.tweenManager);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if (selectedPerson != null && startPoint != null)
        {
            // TODO: update hard-coded values
            
            int finalYPosition = MathHelper.Clamp(screenY, (int)GameDimension.PlatformOffset, 480);
            int finalXPosition = MathHelper.Clamp(screenX, 0, 800);
            
            Vector2 finger = new Vector2(finalXPosition, finalYPosition);
                    finger.add(offset);
                    
            PersonView view = selectedPerson.getView();
                       view.setPosition(finger);

            return true;
        }
        
        return true;
    }
}
