package com.mobi.badvibes.controller.gameplay;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.people.logic.ObedientLogic;
import com.mobi.badvibes.model.people.logic.StillLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.util.MathHelper;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;

public class DragGameplay extends Gameplay
{
    public enum DragState
    {
        Free, Held, FallingDown,
    }

    public ArrayList<Person>   personsReference;
    public Vector2             startPoint, offset;
    public Person              selectedPerson;
    private DragState          state;
    private static final float PICKUP_OFFSET = 15f;

    public DragGameplay(World world)
    {
        super(world);

        state = DragState.Free;
        personsReference = world.getPeopleList();

        Tween.registerAccessor(Person.class, new PersonAccessor());
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (state != DragState.Free)
        {
            return false;
        }

        
        Vector2 p = new Vector2(screenX, screenY);

        for (Person person : personsReference)
        {
            PersonView view = person.getView();

            if (view.getBounds().contains(p.x, p.y))
            {
                selectedPerson = person;

                if (selectedPerson.walkingTween != null)
                    selectedPerson.walkingTween.kill();

                
                selectedPerson.getView().setCurrentState(State.PICKED_UP);

                state = DragState.Held;
                MediaPlayer.sfx("drop");
                
                world.removeTargetPosition(selectedPerson);

                Tween.to(person, PersonAccessor.PICKUP_OFFSET, 0.05f).target(0, -PICKUP_OFFSET).ease(Cubic.INOUT).setCallback(new TweenCallback()
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

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (selectedPerson != null)
        {
            int cellXPosition = MathHelper.Clamp((int) (screenX / GameDimension.Cell.x), 0, World.GRID_WIDTH - 1);
            int cellYPosition = MathHelper.Clamp((int) ((screenY - GameDimension.PlatformOffset) / GameDimension.Cell.y), 0, World.GRID_HEIGHT - 1);

            Point newPoint = new Point(cellXPosition, cellYPosition);
			/** Adds this spot as one of the desirable ones */
            world.addTargetPosition(selectedPerson, newPoint);
            PersonView view = selectedPerson.getView();
            view.setPosition(GameUtil.getPlatformVectorCentered(newPoint));
            

            state = DragState.FallingDown;

            endTouch();

            return true;
        }

        return false;
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
                	selectedPerson.setLogic(new ObedientLogic(selectedPerson));
                    selectedPerson.getView().setCurrentState(State.IDLE);
                    selectedPerson = null;
                    startPoint = null;
                    
                }
            }
        }).start(BadVibes.tweenManager);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if (selectedPerson != null && startPoint != null)
        {
            int cellXPosition = MathHelper.Clamp((int) (screenX / GameDimension.Cell.x), 0, World.GRID_WIDTH - 1);
            int cellYPosition = MathHelper.Clamp((int) ((screenY - GameDimension.PlatformOffset) / GameDimension.Cell.y), 0, World.GRID_HEIGHT - 1);

            PersonView view = selectedPerson.getView();
            view.setPosition(GameUtil.getPlatformVectorCentered(new Point(cellXPosition, cellYPosition)));

            return true;
        }

        return false;
    }
}
