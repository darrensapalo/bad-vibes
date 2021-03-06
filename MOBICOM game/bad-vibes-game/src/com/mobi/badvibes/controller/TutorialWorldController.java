package com.mobi.badvibes.controller;

import java.util.Random;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mobi.badvibes.controller.gameplay.DragGameplay;
import com.mobi.badvibes.controller.gameplay.Gameplay;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.TutorialWorld;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.WorldRenderer;

public class TutorialWorldController extends WorldController
{
    private Texture   sprites;
    private Rectangle railPosition;
    private Rectangle platformPosition;
    private UserInterface userInterface;
    

    public TutorialWorldController()
    {
        super(new TutorialWorld());
    }

    protected void Prepare()
    {
        Tween.registerAccessor(Person.class, new PersonAccessor());
        
        world.initialize();
        renderer = new WorldRenderer(world);
        renderer.initialize();
        gameplay.push(new DragGameplay(world));
        
        
//        for (Gameplay g : gameplay)
//            g.initialize();
        
        userInterface = new UserInterface();
        sprites = ContentManager.loadImage("data/game/sprites.png");

        float railWidth = width;
        float heightOfRail = 120;
        float railHeight = width / 800f * heightOfRail;
        railPosition = new Rectangle(0, GameDimension.RailOffset, railWidth, railHeight);

        float platformWidth = width;
        float heightOfPlatform = 400;
        float platformHeight = width / 800f * heightOfPlatform;
        platformPosition = new Rectangle(0, GameDimension.PlatformOffset, platformWidth, platformHeight);
        
    }

    public void update(float delta)
    {
        for (Person person : world.peopleToBeRemoved)
        {
            world.getPeopleInTrainList().remove(person);
            world.getPeopleList().remove(person);
            renderer.removeFromList(person.getView());
        }
        
        for (Person p : world.getPeopleList())
            p.preupdate(delta);
        
        for (Person p : world.getPeopleInTrainList())
            p.preupdate(delta);
        
        
        
        world.update(delta);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta)
    {
        shapeRenderer.begin(ShapeType.FilledRectangle);
	        shapeRenderer.setColor(54 / 255f, 52 / 255f, 50 / 255f, 1.0f);
	        shapeRenderer.filledRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    shapeRenderer.end();    
	    
        spriteBatch.begin();
            spriteBatch.draw(
                    sprites,
                    railPosition.x, railPosition.y,
                    railPosition.width, railPosition.height,
                    0, 0,
                    800, 120,
                    true, true
                    );
            spriteBatch.draw(
                    sprites,
                    platformPosition.x, platformPosition.y,
                    platformPosition.width, platformPosition.height,
                    0, 120,
                    800, 400,
                    true, true
                    );
        spriteBatch.end();
        renderer.render(spriteBatch, shapeRenderer, delta);
        userInterface.render(spriteBatch, delta);
    }


	public World getWorld()
    {
        return world;
    }
	
	/*
	 * 
	 */
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}
}
