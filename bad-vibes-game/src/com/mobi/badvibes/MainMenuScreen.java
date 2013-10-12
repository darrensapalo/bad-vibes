package com.mobi.badvibes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen implements Screen, InputProcessor {

	private SpriteBatch batch = new SpriteBatch();
	private OrthographicCamera camera;

	// For debugging purposes
	private ShapeRenderer debugRenderer = new ShapeRenderer();

	public MainMenuScreen() {

		// non-power of two images
		Texture.setEnforcePotImages(false);

		batch = new SpriteBatch();
		
		// we don't set the camera here. We just do asset initialization here.
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// displaying things in 3D requires three matrices:
		// projection matrix which dictates where the pixels will be rasterized in the screen
		// view matrix which dictates where an object is in the world
		// and a world matrix which is pretty similar to view matrix (thus i'm not setting it, thus having a value of Matrix.Identity)
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);

		batch.begin();

		batch.end();

		debugRender();
	}

	private void debugRender() {

		// same goes to your debug renderer
		debugRenderer.setProjectionMatrix(camera.projection);
		debugRenderer.setTransformMatrix(camera.view);

		debugRenderer.begin(ShapeType.Rectangle);

		debugRenderer.setColor(new Color(0, 0, 0, 1));
		debugRenderer.rect(0, 0, 200, 200);

		debugRenderer.rect(10, 10, 200, 200);

		debugRenderer.end();
	}

	@Override
	public void resize(int width, int height) {

		int w = Gdx.graphics.getWidth();
		int h = -Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		camera.position.set(w / 2, h / 2, 0);

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
