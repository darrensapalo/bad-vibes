package com.mobi.badvibes;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mobi.badvibes.controller.TutorialWorldController;
import com.mobi.badvibes.controller.WorldController;
import com.mobi.badvibes.model.localstorage.LocalStorage;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.nimators.TrainAccessor;
import com.mobi.badvibes.nimators.WorldRendererAccessor;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.TrainView;
import com.mobi.badvibes.view.WorldRenderer;

public class GameScreen extends BadVibesScreen
{
    public WorldController controller;

    private ShapeRenderer  shapeRenderer;

    @Override
    protected void initialize()
    {
        // BGM
        /* Instantiation */
        
        controller      = new TutorialWorldController();
        shapeRenderer   = new ShapeRenderer();

        /* Tweens */

        Tween.registerAccessor(TrainView.class,         new TrainAccessor());
        Tween.registerAccessor(GameScreen.class,        new BadVibesScreenAccessor());
        Tween.registerAccessor(PersonView.class,        new PersonAccessor());
        Tween.registerAccessor(WorldRenderer.class,     new WorldRendererAccessor());

        setScreenOpacity(1.0f);
        // Timeline.createSequence().push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic)).start(BadVibes.tweenManager);



    }

    @Override
    protected void renderScreen(float delta)
    {
        // Prepare sprite batch and default renderer
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);

        // Update and draw
        controller.update(delta);
        controller.draw(spriteBatch, shapeRenderer, delta);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void pause()
    {
        controller.onPause();
        super.pause();
    }

    @Override
    public void resume()
    {
        controller.onResume();
        super.resume();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return controller.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return controller.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return controller.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        // TODO Auto-generated method stub
        return false;
    }
}
