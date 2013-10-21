package com.mobi.badvibes;


import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.mobi.badvibes.controller.WorldController;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.view.WorldRenderer;


public class GameScreen extends BadVibesScreen {
	private WorldController controller;
	private WorldRenderer renderer;
	
	private int width, height;
	
	private Texture sprites;
	
	@Override
	protected void Initialize() {
		// TODO Auto-generated method stub
		sprites = ContentManager.loadImage("data/game/sprites.png");
		width = (int)(1280 * 0.65f);
		height = (int)(578 * 0.65f);
		
		screenOpacity = 0.1f;
		
		Tween.registerAccessor(GameScreen.class, new BadVibesScreenAccessor());
        Timeline.createSequence()
        .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.2f).target(1).ease(TweenEquations.easeInCubic))
        .start(tweenManager);
	}
	
	@Override
	public void render(float delta) {
        super.render(delta);
		
		Gdx.gl.glClearColor((float)52/255, (float)49/255, (float)47/255, screenOpacity);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);
		spriteBatch.begin();
			spriteBatch.setColor(1.0f, 1.0f, 1.0f, screenOpacity);
			spriteBatch.draw(sprites, 0, 40, 1280, 187, 0, 0, 1280, 187, true, true);
			spriteBatch.draw(sprites, 0, 180, width, height, 0, 187, 1280, 578, true, true);
		spriteBatch.end();
		
		
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
