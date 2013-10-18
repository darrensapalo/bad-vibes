package com.mobi.badvibes;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen, InputProcessor {

	private SpriteBatch batch = new SpriteBatch();
	private OrthographicCamera camera;
	private Texture splash;
	private Point splashPos;
	private float splashOpacity;
	
	private float counter;
	private static TweenManager tweenManager;
	
	
	// For debugging purposes
	public SplashScreen() {

		// non-power of two images
		Texture.setEnforcePotImages(false);
		
		splash = new Texture(Gdx.files.internal("data/dlsu.png"));
		int x = Gdx.graphics.getWidth() / 2 - splash.getWidth() / 4;
		int y = Gdx.graphics.getHeight() / 2 - splash.getHeight() / 4;
		counter = 0;
		splashOpacity = 0.02f;
		
		splashPos = new Point(x, y);
		batch = new SpriteBatch();
		
		
		// Tween
		setTweenManager(new TweenManager()); 
		Tween.setCombinedAttributesLimit(1);
		
		Tween.registerAccessor(SplashScreen.class, new SplashScreenAccessor());
		
	    
	    
	    Timeline.createSequence()
	    .push(Tween.to(this, SplashScreenAccessor.OPACITY, 0.7f)         
	    	    .target(1.0f)         
	    	    .ease(TweenEquations.easeInCubic)) // First, set all objects to their initial positions
	    .push(Tween.to(this, SplashScreenAccessor.OPACITY, 1.1f)         
	    	    .target(0.0f)         
	    	    .ease(TweenEquations.easeInCubic)).start(tweenManager);
	}

	private void setTweenManager(TweenManager tweenManager) {
		this.tweenManager = tweenManager;
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);
		
		tweenManager.update(delta);
		
		batch.begin();	
			batch.setColor(1.0f, 1.0f, 1.0f, splashOpacity);
			batch.draw(splash, splashPos.x, splashPos.y, splash.getWidth() / 2, splash.getHeight() / 2, 0, 0, splash.getWidth(), splash.getHeight(), false, true);
		batch.end();

	}
	
	public float getSplashOpacity() {
		return splashOpacity;
	}

	public void setSplashOpacity(float splashOpacity) {
		this.splashOpacity = splashOpacity;
	}

	private void updateSplash(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {

		int w = Gdx.graphics.getWidth();
		int h = -Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		camera.position.set(0, 0, 0);
		camera.setToOrtho(true);
		camera.update();
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}

}
