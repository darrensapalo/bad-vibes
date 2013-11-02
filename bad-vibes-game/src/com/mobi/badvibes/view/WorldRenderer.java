package com.mobi.badvibes.view;

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
	
	public WorldRenderer(World world) {
		this.world = world;
	}

	public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta) {
		drawTiles(shapeRenderer);
		
		for(Person p : world.getPeopleList()){
			p.render(spriteBatch, delta);

			/*
			Rectangle bounds = p.getView().getBounds();
			shapeRenderer.begin(ShapeType.Rectangle);
				shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			shapeRenderer.end();
			*/
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
