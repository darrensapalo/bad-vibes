package com.mobi.badvibes.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mobi.badvibes.controller.gameplay.DragGameplay;
import com.mobi.badvibes.controller.gameplay.RushGameplay;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.WorldRenderer;

public class TutorialWorldController extends WorldController {

	private Texture   sprites;
	private Rectangle railPosition;
	private Rectangle platformPosition;

	public TutorialWorldController(World world) {
		super(world);
	}	
	
	protected void Initialize() {
		renderer = new WorldRenderer(world);
		gameplay.push(new DragGameplay(world));
		gameplay.push(new RushGameplay(world));
        sprites     = ContentManager.loadImage("data/game/sprites.png");
        
        float railWidth = width;
		float heightOfRail = 120;
		float railHeight = width / 800f * heightOfRail;
		railPosition = new Rectangle(0, GameDimension.RailOffset, railWidth, railHeight);
		
        float platformWidth = width;
		float heightOfPlatform = 400;
		float platformHeight = width / 800f * heightOfPlatform;
		platformPosition = new Rectangle(0, GameDimension.PlatformOffset, platformWidth, platformHeight);
		
	}
	
	public void update(float delta) {
		
	}


	public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta) {
		shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(54/255f, 52/255f, 50/255f, 1.0f);
        
        shapeRenderer.filledRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        
        
        spriteBatch.begin();
	        spriteBatch.draw(sprites, railPosition.x, railPosition.y, railPosition.width, railPosition.height, 0, 0, 800, 120, true, true);
	        spriteBatch.draw(sprites, platformPosition.x, platformPosition.y, platformPosition.width, platformPosition.height, 0, 120, 800, 400, true, true);
        spriteBatch.end();
        
        drawTiles(shapeRenderer);
        
        renderer.render(spriteBatch, shapeRenderer, delta);
	}
	
    private void drawTiles(ShapeRenderer shapeRenderer) {
    	
    	shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.setColor(Color.RED);
		
		for (int y = 0; y < World.GRID_HEIGHT * 2; y++)
			for (int x = 0; x < World.GRID_WIDTH * 2; x++)
			shapeRenderer.rect(GameDimension.X_OFFSET + x * GameDimension.Cell.x, GameDimension.PlatformOffset + y * GameDimension.Cell.y, GameDimension.Cell.x, GameDimension.Cell.y);
		shapeRenderer.end();
	}
    

	public World getWorld() {
		return world;
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		
	}
}
