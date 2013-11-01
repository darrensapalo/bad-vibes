package com.mobi.badvibes.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;

public class WorldRenderer {

	private World world;

	public static final float GRID_CELL_HEIGHT = 80f;
	public static final float GRID_CELL_WIDTH = 80f;
	
	public static final float PERSON_WIDTH = 52f;
	public static final float PERSON_HEIGHT = 82.67f;

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
		Array<Person> peopleList = world.getPeopleList();
		for(Person p : peopleList){
			p.render(spriteBatch, delta);

			Rectangle bounds = p.getView().getBounds();
			shapeRenderer.begin(ShapeType.Rectangle);
				shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			shapeRenderer.end();
		}
		
	}

}
