package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.nimators.PreGameScreenAccessor;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.WorldRenderer;

/**
 * This class shows the textual instructions on how to
 * win the game to be played.
 * Before instructing the GameMaster to prepare the game,
 * ensure that setInformation(String, String) was called. 
 * @see GameMaster
 * @author Darren
 *
 */
public class PreGameScreen extends BadVibesScreen implements TweenCallback
{
    // For debugging purposes
    private BitmapFont      titleFont;
    private BitmapFont      defaultFont;
    
    private Point           titlePos;
    private Point           subtitlePos;
    
	private CharSequence 	titleHeader;
	private CharSequence 	subtitleHeader;
	private ShapeRenderer shapeRenderer;
	private Texture sprites;
	private Rectangle railPosition;
	private Rectangle platformPosition;
	
	private float textOpacity;
	
    @Override
    protected void initialize()
    {
        // non-power of two images

        MediaPlayer.bgm("game");
        Texture.setEnforcePotImages(false);

        shapeRenderer   = new ShapeRenderer();
        
        sprites = ContentManager.loadImage("data/game/sprites.png");
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        
        float railWidth = width;
        float heightOfRail = 120;
        float railHeight = width / 800f * heightOfRail;
        railPosition = new Rectangle(0, GameDimension.RailOffset, railWidth, railHeight);

        float platformWidth = width;
        float heightOfPlatform = 400;
        float platformHeight = width / 800f * heightOfPlatform;
        platformPosition = new Rectangle(0, GameDimension.PlatformOffset, platformWidth, platformHeight);

        
        
        
        // create fonts
        titleFont = new BitmapFont(Gdx.files.internal("data/Arial65.fnt"), Gdx.files.internal("data/Arial65.png"), true);
        titleFont.setColor(0f, 0f, 0f, 1f);

        defaultFont = new BitmapFont(Gdx.files.internal("data/Arial20.fnt"), Gdx.files.internal("data/Arial20.png"), true);
        defaultFont.setColor(0f, 0f, 0f, 1f);
        
        float scaleX = width / GameDimension.TargetDimension().x;
        float scaleY = height / GameDimension.TargetDimension().y;
        titleFont.setScale(scaleX, scaleY);
        defaultFont.setScale(scaleX, scaleY);

        updatePosition();
        Tween.registerAccessor(PreGameScreen.class, new PreGameScreenAccessor());
        setScreenOpacity(0f);
        Timeline.createSequence()
        .push(Tween.to(this, PreGameScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic))
        .push(Tween.to(this, PreGameScreenAccessor.TEXT_OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic).delay(1.0f))
        .push(Tween.to(this, PreGameScreenAccessor.TEXT_OPACITY, 0.5f).target(0).ease(TweenEquations.easeInCubic).delay(2.5f)
    															  .setCallbackTriggers(TweenCallback.END).setCallback(new TweenCallback() {
																	@Override
																	public void onEvent(int arg0, BaseTween<?> arg1) {
																		GameMaster.startGame();
																	}
																}))
        .start(BadVibes.tweenManager);
    }
    
    public void setInformation(String title, String subtitle){
    	this.titleHeader = title;
    	this.subtitleHeader = subtitle;
    }

    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(54 / 255f, 52 / 255f, 50 / 255f, 1.0f);
        shapeRenderer.filledRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    shapeRenderer.end();    
    
    spriteBatch.begin();
        spriteBatch.draw(
                sprites,
                railPosition.x, railPosition.y,
                railPosition.width, railPosition.height,
                0, 0,
                800, 120,
                true, true
                );
        spriteBatch.draw(
                sprites,
                platformPosition.x, platformPosition.y,
                platformPosition.width, platformPosition.height,
                0, 120,
                800, 400,
                true, true
                );
    spriteBatch.end();
        
        spriteBatch.begin();
        Color c = Color.BLACK;
        titleFont.setColor(c.r, c.g, c.b, textOpacity);
        defaultFont.setColor(c.r, c.g, c.b, textOpacity);
        titleFont.draw(spriteBatch, titleHeader, titlePos.x, titlePos.y);
        defaultFont.draw(spriteBatch, subtitleHeader, subtitlePos.x, subtitlePos.y);
        spriteBatch.end();
    }

    private void updatePosition()
    {
    	float textWidth = 0;
    	int x = 0;
    	int y = 0;

    	Vector2 title = new Vector2(titleFont.getBounds(titleHeader).width, titleFont.getBounds(titleHeader).height);
    	Vector2 subtitle = new Vector2(defaultFont.getBounds(subtitleHeader).width, defaultFont.getBounds(subtitleHeader).height);
    	Vector2 target;
    	target = GameDimension.Viewport();
    	titlePos = new Point(target.div(2).sub(title.div(2)));
    	target = GameDimension.Viewport();
    	subtitlePos = new Point(target.div(2).sub(subtitle.div(2)).add(0, title.y * 2f));
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
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

	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		GameMaster.startGame();
		
	}

	public float getTextOpacity() {
		return textOpacity;
	}

	public void setTextOpacity(float textOpacity) {
		this.textOpacity = textOpacity;
	}
}
