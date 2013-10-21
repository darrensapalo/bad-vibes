package com.mobi.badvibes;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BadVibesScreen implements Screen, InputProcessor{

	protected TweenManager tweenManager = new TweenManager();
    protected SpriteBatch spriteBatch = new SpriteBatch();
    protected OrthographicCamera camera;
    

    protected float screenOpacity;
	
	public BadVibesScreen(){
		// Initialize camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), -Gdx.graphics.getHeight());
        camera.position.set(0, 0, 0);
        camera.setToOrtho(true);
        camera.update();
		
		Initialize();
	}
	
	protected abstract void Initialize();

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
    public void render(float delta){
    	tweenManager.update(delta);
    }
    
	public TweenManager getTweenManager() {
		return tweenManager;
	}

	public float getScreenOpacity() {
		return screenOpacity;
	}

	public void setScreenOpacity(float screenOpacity) {
		this.screenOpacity = screenOpacity;
	}
}
