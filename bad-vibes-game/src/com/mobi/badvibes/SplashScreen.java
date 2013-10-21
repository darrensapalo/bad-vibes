package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;

public class SplashScreen extends BadVibesScreen
{
	private Texture splash;
    private Point splashPos;

	@Override
	protected void Initialize() {
		// TODO Auto-generated method stub
	
        // non-power of two images
        Texture.setEnforcePotImages(false);

        splash = new Texture(Gdx.files.internal("data/dlsu.png"));

        updatePosition();
        
        // Tween
        Tween.setCombinedAttributesLimit(1);
        Tween.registerAccessor(SplashScreen.class, new BadVibesScreenAccessor());

        Timeline.createSequence()
            .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1.0f).ease(TweenEquations.easeInCubic))
            .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(0.0f).ease(TweenEquations.easeInCubic).delay(0.5f))
            .setCallbackTriggers(TweenCallback.END)
            .setCallback(new TweenCallback()
            {
                @Override
                public void onEvent(int type, BaseTween<?> source)
                {
                    if (type == TweenCallback.END)
                    {
                        BadVibes.getInstance().setScreen(new MainMenuScreen());
                    }
                }
            })
            .start(tweenManager);
    }

    @Override
    public void render(float delta)
    {
    	super.render(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        spriteBatch.begin();
        spriteBatch.setColor(1.0f, 1.0f, 1.0f, screenOpacity);
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
    public void dispose()
    {
        spriteBatch.dispose();
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
