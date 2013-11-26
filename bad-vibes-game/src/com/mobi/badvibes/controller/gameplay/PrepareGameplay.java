package com.mobi.badvibes.controller.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.model.world.World.WorldState;
import com.mobi.badvibes.view.GameDimension;

public class PrepareGameplay extends GameplayStrategy
{
    public static Vector2 destination;
    public ArrayList<Point> positions;
    
    public PrepareGameplay(World world)
    {
        super(world);
        destination = new Vector2();
        positions = world.getTargetPositions();
    }
        
    private Point getPoint(float screenX, float screenY){
    	float y = screenY - GameDimension.PlatformOffset;
    	float x = screenX;
    	
    	int cellX = (int)x / (int)GameDimension.MiniCell.x;
    	int cellY =  (int)y / (int)GameDimension.MiniCell.y;
    	return new Point(cellX, cellY);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
    	/* Tapping on a cell that was set to be a destination/part of the queue will remove it */
    	if (world.getCurrentState() == WorldState.ENTERING){
	    	Point toAdd = getPoint(screenX, screenY);
	    	if (positions.contains(toAdd)){
	    		positions.remove(toAdd);
	    	}
	        return true;
    	}
    	return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return true;
    }
}
