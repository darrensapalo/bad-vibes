package com.mobi.badvibes.controller;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mobi.badvibes.controller.gameplay.GameplayStrategy;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.WorldRenderer;

public abstract class WorldController
{
    protected World                   world;
    protected WorldRenderer           renderer;

    protected Stack<GameplayStrategy> gameplay;

    protected int                     width;
    protected int                     height;

    public WorldController(World world)
    {
        this.world = world;

        gameplay = new Stack<GameplayStrategy>();

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        Prepare();
    }

    /**
     * This handles the selection of a renderer, gameplay mechanics,
     * spritesheets, and more. This will vary depending on level design.
     * 
     * @author Darren
     */
    protected abstract void Prepare();

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta);

    public World getWorld()
    {
        return world;
    }

    public abstract void onPause();

    public abstract void onResume();

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        boolean value = false;
        for (GameplayStrategy gs : gameplay)
        {
            if (value = gs.touchDown(screenX, screenY, pointer, button) || value)
                return value;
        }
        return value;

    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        boolean value = false;
        for (GameplayStrategy gs : gameplay)
        {
            if (value = gs.touchUp(screenX, screenY, pointer, button) || value)
                return value;
        }
        return value;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        boolean value = false;
        for (GameplayStrategy gs : gameplay)
        {
            if (value = gs.touchDragged(screenX, screenY, pointer) || value)
                return value;
        }
        return value;
    }
}
