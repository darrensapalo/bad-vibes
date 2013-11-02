package com.mobi.badvibes;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public abstract class BadVibesScreen implements Screen, InputProcessor
{
    
    protected SpriteBatch        spriteBatch   = new SpriteBatch();
    protected OrthographicCamera camera        = null;

    private FrameBuffer          frameBuffer   = null;
    private TextureRegion        textureRegion = null;

    protected float              screenOpacity = 0;

    public BadVibesScreen()
    {
        // Initialize camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), -Gdx.graphics.getHeight());
        camera.position.set(0, 0, 0);
        camera.setToOrtho(true);
        camera.update();

        frameBuffer     = new FrameBuffer(Format.RGB888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        textureRegion   = new TextureRegion(frameBuffer.getColorBufferTexture(), frameBuffer.getWidth(), frameBuffer.getHeight());

        initialize();
    }

    public float getScreenOpacity()
    {
        return screenOpacity;
    }

    public void setScreenOpacity(float screenOpacity)
    {
        this.screenOpacity = screenOpacity;
    }

    protected abstract void initialize();

    protected abstract void renderScreen(float delta);

    @Override
    public void resize(int width, int height)
    {
        int w = Gdx.graphics.getWidth();
        int h = -Gdx.graphics.getHeight();

        camera = new OrthographicCamera(w, h);
        camera.position.set(0, 0, 0);
        camera.setToOrtho(true);
        camera.update();
    }

    @Override
    public void render(float delta)
    {
        frameBuffer.begin();
        BadVibes.tweenManager.update(delta);
        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.setColor(1, 1, 1, 1);
        
        renderScreen(delta);

        frameBuffer.end();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.setColor(1, 1, 1, screenOpacity);
        
        spriteBatch.draw(textureRegion.getTexture(), 0, 0, frameBuffer.getWidth(), frameBuffer.getHeight());
        
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        spriteBatch.dispose();
        frameBuffer.dispose();
    }
}
