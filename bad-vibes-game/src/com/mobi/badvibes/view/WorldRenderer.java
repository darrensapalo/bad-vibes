package com.mobi.badvibes.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mobi.badvibes.model.world.World;

/**
 * This class handles the rendering of the world. It runs all person's logic and
 * rendering.
 * 
 * @author Darren
 * 
 */
public class WorldRenderer
{
    public static WorldRenderer             Instance;

    private World                           world;

    public World getWorld()
    {
        return world;
    }
    
    public static final float               RAIL_WIDTH        = 800;
    public static final float               RAIL_HEIGHT       = 120;

    public static final float               PLATFORM_WIDTH    = 800;
    public static final float               PLATFORM_HEIGHT   = 400;

    public static final float               CELL_HEIGHT       = 40;
    public static final float               CELL_WIDTH        = 40;

    public static float                     X_OFFSET          = 0;
    public static float                     PLATFORM_Y_OFFSET = 85;

    public static float                     RAIL_Y_OFFSET     = 25;

    public ArrayList<ArrayList<PersonView>> masterBucket;

    public WorldRenderer(World theWorld)
    {
        Instance    = this;
        world       = theWorld;

        // initialize the buckets
        masterBucket = new ArrayList<ArrayList<PersonView>>();
        
        for (int i = 0; i < World.GRID_HEIGHT; i++)
        {
            masterBucket.add(new ArrayList<PersonView>());   
        }
    }

    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta)
    {
        // drawTiles(shapeRenderer);
        
        world.getTrain().trainView.render(spriteBatch, delta);
        
        for (int i = 0; i < World.GRID_HEIGHT; i++)
        {
            for (PersonView p : masterBucket.get(i))
            {
                p.render(spriteBatch, delta);   
            }
        }
    }

    public boolean masterBucketContains(PersonView p)
    {
        for (int i = 0; i < World.GRID_HEIGHT; i++)
        {
            if (masterBucket.get(i).contains(p))
                return true;

        }
        return false;
    }

    public void addToList(PersonView p, int bucketID)
    {
        removeFromList(p);

        if (masterBucketContains(p) == false)
            masterBucket.get(bucketID).add(p);
    }

    private void removeFromList(PersonView p)
    {
        for (int i = 0; i < World.GRID_HEIGHT; i++)
        {
            if (masterBucket.get(i).contains(p))
            {
                masterBucket.get(i).remove(p);
            }
        }
    }

    private void drawTiles(ShapeRenderer shapeRenderer)
    {
        shapeRenderer.begin(ShapeType.Rectangle);
            
            shapeRenderer.setColor(Color.RED);
    
            for (int y = 0; y < World.GRID_HEIGHT; y++)
                for (int x = 0; x < World.GRID_WIDTH; x++)
                    shapeRenderer.rect(
                            GameDimension.X_OFFSET + x * GameDimension.MiniCell.x,
                            GameDimension.PlatformOffset + y * GameDimension.MiniCell.y,
                            GameDimension.MiniCell.x,
                            GameDimension.MiniCell.y);
            
        shapeRenderer.end();
    }
}
