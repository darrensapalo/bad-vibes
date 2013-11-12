package com.mobi.badvibes.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;

/**
 * This class handles the rendering of the world. It runs all 
 * person's logic and rendering.
 * @author Darren
 *
 */
public class WorldRenderer {

	public static WorldRenderer Instance;
	
	private World world;

	public static final float GRID_CELL_HEIGHT = 80f;
	public static final float GRID_CELL_WIDTH = 80f;
	
	public static final float RAIL_WIDTH = 800;
	public static final float RAIL_HEIGHT = 120;
	
	public static final float PLATFORM_WIDTH = 800;
	public static final float PLATFORM_HEIGHT = 400;

	public static final float CELL_HEIGHT = 80;
	public static final float CELL_WIDTH = 80;
	
	public static float X_OFFSET = 0;
	public static float PLATFORM_Y_OFFSET = 130;
	
	public static float RAIL_Y_OFFSET = 25;
	
	public ArrayList<ArrayList<PersonView>> masterBucket;
	
	public WorldRenderer(World world) {
		Instance = this;
		this.world = world;
		
		// initialize the buckets
		masterBucket = new ArrayList<ArrayList<PersonView>>();
		for(int i = 0; i < World.MINI_GRID_HEIGHT; i++)
			masterBucket.add(new ArrayList<PersonView>());
	}

	public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta) {
		
		// drawTiles(shapeRenderer);
		for (int i = 0; i < World.MINI_GRID_HEIGHT; i++)
			for(PersonView p : masterBucket.get(i)) 
				p.render(spriteBatch, delta);
		
		
	}
	
	private void removeFromList(PersonView p){
		for (int i = 0; i < World.MINI_GRID_HEIGHT; i++){
			if (masterBucket.get(i).contains(p)){
				masterBucket.get(i).remove(p);
			}
		}
	}
	
	public boolean masterBucketContains(PersonView p){
		for (int i = 0; i < World.MINI_GRID_HEIGHT; i++){
			if (masterBucket.get(i).contains(p))
				return true;
			
		}
		return false;
	}
	
	public void addToList(PersonView p, int bucketID){
		try {
			removeFromList(p);
			
			if (bucketID < 0 || bucketID > World.MINI_GRID_HEIGHT)
				throw new Exception("Incorrect bucket ID used: " + bucketID);
			if (masterBucketContains(p) == false)
				masterBucket.get(bucketID).add(p);
			
		}catch(Exception e){
			// e.printStackTrace();
		}
	}
	
	private void drawTiles(ShapeRenderer shapeRenderer) {
    	
    	shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.setColor(Color.RED);
		
		for (int y = 0; y < World.MINI_GRID_HEIGHT; y++)
			for (int x = 0; x < World.MINI_GRID_WIDTH; x++)
			shapeRenderer.rect(GameDimension.X_OFFSET + x * GameDimension.MiniCell.x, GameDimension.PlatformOffset + y * GameDimension.MiniCell.y, GameDimension.MiniCell.x, GameDimension.MiniCell.y);
		shapeRenderer.end();
	}

}
