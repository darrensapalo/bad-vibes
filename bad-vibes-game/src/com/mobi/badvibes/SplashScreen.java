package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.TrainView;

public class SplashScreen extends BadVibesScreen
{
    private Texture splash;
    private Point   splashPos;

    @Override
    protected void initialize()
    {
        // Initializes cache of person views
        PersonView.Initialize();
        TrainView.Initialize();

        // non-power of two images
        Texture.setEnforcePotImages(false);

        splash = new Texture(Gdx.files.internal("data/dlsu.png"));

        updatePosition();

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
        spriteBatch.draw(splash, splashPos.x, splashPos.y, splash.getWidth() / 2, splash.getHeight() / 2, 0, 0, splash.getWidth(), splash.getHeight(), false, true);
        spriteBatch.end();
    }

    private void updatePosition()
    {
        int x = Gdx.graphics.getWidth() / 2 - splash.getWidth() / 4;
        int y = Gdx.graphics.getHeight() / 2 - splash.getHeight() / 4;

        splashPos = new Point(x, y);
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
