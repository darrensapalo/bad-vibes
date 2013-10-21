package com.mobi.badvibes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class StatisticsScreen extends BadVibesScreen {

	private SpriteBatch batch = new SpriteBatch();
	private OrthographicCamera camera;

	// For debugging purposes
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private BitmapFont titleFont;
	private BitmapFont defaultFont;
	private Point titlePos;
	private Point subtitlePos;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private Animation walkAnimation;
	private TextureRegion currentFrame;
	private Point position;
	private boolean toTheLeft;
	private float stateTime;
	private Color grayColor;

	@Override
	protected void Initialize() {
		
		// non-power of two images
		Texture.setEnforcePotImages(false);
		titleFont = new BitmapFont(Gdx.files.internal("data/InconsolataTitle.fnt"), Gdx.files.internal("data/InconsolataTitle.png"), true);
		titleFont.setColor(0f, 0f, 0f, 0.15f);
		
		// programmed x location 
		float textWidth = titleFont.getBounds("Bad Vibes").width;
		int x = Gdx.graphics.getWidth() / 2 - (int)textWidth / 2;
		int y = 200;
		
		titlePos = new Point(x, y);
		
		defaultFont = new BitmapFont(Gdx.files.internal("data/Inconsolata.fnt"), Gdx.files.internal("data/Inconsolata.png"), true);
		defaultFont.setColor(0f, 0f, 0f, 0.15f);
		
		// programmed x location
		textWidth = defaultFont.getBounds("TAP SCREEN TO START").width;
		x = Gdx.graphics.getWidth() / 2 - (int)textWidth / 2;
		y = 260;
		
		int FRAME_COLS = 3;
		int FRAME_ROWS = 1;
		walkSheet = new Texture(Gdx.files.internal("data/RunAround.png"));     // #9
		
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS); // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                        walkFrames[index++] = tmp[i][j];
                }
        }
        
        walkAnimation = new Animation(0.1f, walkFrames);
        position = new Point(10, Gdx.graphics.getHeight() - 200);
        
        grayColor = Color.BLACK.mul(0.60f);
        
		subtitlePos = new Point(x, y);
		
		batch = new SpriteBatch();
		
		
		// we don't set the camera here. We just do asset initialization here.
	}

	@Override
	public void render(float delta) {
    	super.render(delta);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// displaying things in 3D requires three matrices:
		// projection matrix which dictates where the pixels will be rasterized in the screen
		// view matrix which dictates where an object is in the world
		// and a world matrix which is pretty similar to view matrix (thus i'm not setting it, thus having a value of Matrix.Identity)
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);

		if (toTheLeft){
			position.x -= 5;
			if (position.x <= 0)
				toTheLeft = false;
		}else{
			position.x += 5;
			if (position.x >= Gdx.graphics.getWidth() - 150)
				toTheLeft = true;
		}
		
		
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();	
			Color preColor = batch.getColor();
			titleFont.draw(batch, "Bad Vibes", titlePos.x, titlePos.y);
			defaultFont.draw(batch, "TAP SCREEN TO START", subtitlePos.x, subtitlePos.y);
			
			
			batch.setColor(grayColor);
			batch.draw(currentFrame, position.x, position.y, 100, 100, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), (toTheLeft == false) ? 1.0f : -1.0f, 1.0f, 180f);
			batch.setColor(preColor);
		batch.end();

		if (false)
			debugRender();
	}

	private void debugRender() {

		// same goes to your debug renderer
		debugRenderer.setProjectionMatrix(camera.projection);
		debugRenderer.setTransformMatrix(camera.view);

		debugRenderer.begin(ShapeType.Rectangle);

		debugRenderer.setColor(new Color(1, 0, 0, 1));
		debugRenderer.rect(60, 60, 100, 100);
		debugRenderer.rect(10, 10, 50, 50);

		debugRenderer.end();
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
