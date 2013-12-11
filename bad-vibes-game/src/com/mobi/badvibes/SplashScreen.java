package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.TrainView;
import com.mobi.badvibes.view.graphics.BVTexture;

public class SplashScreen extends BadVibesScreen
{
    private BVTexture splash;
    private Vector2   splashPos;

    @Override
    protected void initialize()
    {
    	// BGM
    	MediaPlayer.bgm("mainmenu");
    	
        // Initializes cache of person views
        PersonView.Initialize();
        TrainView.Initialize();

        // non-power of two images
        Texture.setEnforcePotImages(true);

        splash = new BVTexture(Gdx.files.internal("data/dlsu.png"));
        splash.setScaleModifier(0.6f);

        splashPos = splash.centerAt(GameDimension.Viewport());
        

        // Tween
        Tween.setCombinedAttributesLimit(3);
        Tween.registerAccessor(SplashScreen.class, new BadVibesScreenAccessor());

        Timeline.createSequence().push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1.0f).ease(TweenEquations.easeInCubic)).push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(0.0f).ease(TweenEquations.easeInCubic).delay(1.0f)).setCallbackTriggers(TweenCallback.END).setCallback(new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                if (type == TweenCallback.END)
                {
                    BadVibes.getInstance().setScreen(BadVibes.mainMenuScreen);
                }
            }
        }).start(BadVibes.tweenManager);
    }

    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        spriteBatch.begin();
        splash.draw(spriteBatch, splashPos);
        spriteBatch.end();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

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
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
