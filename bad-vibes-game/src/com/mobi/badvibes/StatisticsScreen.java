package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;
import com.mobi.badvibes.view.graphics.BVTexture;
import com.mobi.badvibes.view.graphics.BVTextureRegion;

/**
 * This class shows the textual instructions on how to
 * win the game to be played.
 * Before instructing the GameMaster to prepare the game,
 * ensure that setInformation(String, String) was called. 
 * @see GameMaster
 * @author Darren
 *
 */
public class StatisticsScreen extends BadVibesScreen implements TweenCallback
{
    // For debugging purposes
    private BitmapFont      titleFont;
    private BitmapFont      defaultFont;
    
    private Point           titlePos;
    private Point           subtitlePos;
    
    private CharSequence  titleHeader;
    private CharSequence  subtitleHeader;
    private ShapeRenderer shapeRenderer;
    private Rectangle     railPosition;
    private Rectangle     platformPosition;
    private Texture       sprites;
    private float         textOpacity;
    private BVTexture levelClearedSpriteSheet;
    private BVTextureRegion starFilled;
    private BVTextureRegion starNotFilled;
    private BVTextureRegion tapToContinue;
    private Vector2 p_star1;
    private Vector2 p_star2;
    private Vector2 p_star3;
    private Vector2 p_tapToContinue;
    private BVTextureRegion levelCleared;
    private Vector2 p_levelCleared;
    private boolean hasPressed;

    @Override
    protected void initialize()
    {
        // non-power of two images
        Texture.setEnforcePotImages(false);

        hasPressed = false;
        levelClearedSpriteSheet = new BVTexture(Gdx.files.internal("data/level cleared/level cleared.png"));
        
        levelCleared = new BVTextureRegion(levelClearedSpriteSheet, new Rectangle(0, 0, 447, 60));
        p_levelCleared = new Vector2(190, 55);
        
        starFilled = new BVTextureRegion(levelClearedSpriteSheet, new Rectangle(0, 60, 120, 134));
        starNotFilled = new BVTextureRegion(levelClearedSpriteSheet, new Rectangle(120, 60, 120, 134));
        
        p_star1 = GameUtil.getScaledVector(new Vector2(205, 130));
        p_star2 = GameUtil.getScaledVector(new Vector2(350, 130));
        p_star3 = GameUtil.getScaledVector(new Vector2(495, 130));
        
        
        tapToContinue = new BVTextureRegion(levelClearedSpriteSheet, new Rectangle(240, 60, 186, 22));
        p_tapToContinue = GameUtil.getScaledVector(new Vector2(320, 450));
        
        
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
        
        textOpacity = 1f;

        Tween.registerAccessor(StatisticsScreen.class, new BadVibesScreenAccessor());

        Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic).start(BadVibes.tweenManager);
        
      
    }
    
    public void setInformation(String title, String subtitle){
    	this.titleHeader = title;
    	this.subtitleHeader = subtitle;
    	updatePosition();
    }

    @Override
    protected void renderScreen(float delta)
    {

        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);
        
        drawBackground(delta);
        
        spriteBatch.begin();
            levelCleared.draw(spriteBatch, p_levelCleared);
            float score = GameMaster.score;
            BVTextureRegion star1 = (score > 0.35f) ? starFilled : starNotFilled;
            BVTextureRegion star2 = (score > 0.70f) ? starFilled : starNotFilled;
            BVTextureRegion star3 = (score > 0.90f) ? starFilled : starNotFilled;
            star1.draw(spriteBatch, p_star1);
            star2.draw(spriteBatch, p_star2);
            star3.draw(spriteBatch, p_star3);
            tapToContinue.draw(spriteBatch, p_tapToContinue);
    
            
            Color c = Color.BLACK;
            titleFont.setColor(c.r, c.g, c.b, textOpacity);
            defaultFont.setColor(c.r, c.g, c.b, textOpacity);
            titleFont.draw(spriteBatch, titleHeader, titlePos.x, titlePos.y);
            defaultFont.draw(spriteBatch, subtitleHeader, subtitlePos.x, subtitlePos.y);
        spriteBatch.end();



    }

    private void drawBackground(float delta)
    {
        shapeRenderer.begin(ShapeType.FilledRectangle);
            shapeRenderer.setColor(54 / 255f, 52 / 255f, 50 / 255f, 1.0f);
            shapeRenderer.filledRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    
        spriteBatch.begin();
            spriteBatch.draw(sprites, railPosition.x, railPosition.y, railPosition.width, railPosition.height, 0, 0, 800, 120, true, true);
            spriteBatch.draw(sprites, platformPosition.x, platformPosition.y, platformPosition.width, platformPosition.height, 0, 120, 800, 400, true, true);
        spriteBatch.end();
    }

    private void updatePosition()
    {
        float textWidth = 0;
        int x = 0;
        int y = 0;

        textWidth = titleFont.getBounds(titleHeader).width;
        x = (int) ((Gdx.graphics.getWidth() - textWidth) / 2);
        y = GameUtil.convertToScaledFactor(350);

        titlePos = new Point(x, y);

        textWidth = defaultFont.getBounds(subtitleHeader).width;
        x = (int) ((Gdx.graphics.getWidth() - textWidth) / 2);
        y = GameUtil.convertToScaledFactor(410);

        subtitlePos = new Point(x, y);
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
        if (hasPressed == false)
        {
            hasPressed = true;
            Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(0).ease(TweenEquations.easeInCubic).setCallback(new TweenCallback()
            {

                @Override
                public void onEvent(int arg0, BaseTween<?> arg1)
                {
                    if (GameMaster.rounds >= 0)
                    {
                        GameMaster.prepareGame();
                    }
                    else
                    {
                        BadVibes.getInstance().setScreen(BadVibes.mainMenuScreen);
                        MediaPlayer.bgm("mainmenu");
                    }
                }
            }).start(BadVibes.tweenManager);
        }
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
}
