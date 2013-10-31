package com.mobi.badvibes.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mobi.badvibes.controller.gameplay.GameplayStrategy;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.WorldRenderer;

public abstract class WorldController {

	protected World world;
	protected WorldRenderer renderer;
	
	protected GameplayStrategy gameplay;

    protected int             width;
    protected int				height;
	
	public WorldController(World world){
		this.world = world;
		
		width       = Gdx.graphics.getWidth();
        height		= Gdx.graphics.getHeight();
        Initialize();
	}
	
	protected abstract void Initialize();
	
	public abstract void update(float delta);

	public abstract void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta);
	
    public World getWorld() {
		return world;
	}

	public abstract void onPause();

	public abstract void onResume();

	public boolean touchDown(int screenX, int screenY, int pointer, int button){
		return gameplay.touchDown(screenX, screenY, pointer, button);
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button){
		return gameplay.touchUp(screenX, screenY, pointer, button);
	}

	public boolean touchDragged(int screenX, int screenY, int pointer){
		return gameplay.touchDragged(screenX, screenY, pointer);
	}
}
